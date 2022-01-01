package com.draco.bookalert.services;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Book;
import com.draco.bookalert.models.BookUser;
import com.draco.bookalert.models.User;
import com.draco.bookalert.models.itunes.iTunesBook;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.*;

@Service
public class RefreshService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private iTunesService iTunesService;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private EmailService emailService;

    private void sendEmails(Map<User,List<Book>> map) {

        map.forEach((user, books) -> {
            StringBuilder body = new StringBuilder("<html><body><h1>Hello ")
                    .append(user.getFirst_name())
                    .append(", </h1><h2>You have the following new releases: </h2>");

            for(Book book : books) {
                body.append("<div><img src=")
                        .append(book.getArtwork_url())
                        .append("><div><h3>").append(book.getTitle())
                        .append(", by ").append(book.getAuthor().getName())
                        .append("</h3><a target='_blank' href=").append(book.getItunes_url()).append(">Purchase from iTunes</a></div></div>")
                        .append("<br><br>");
            }
            body.append("</body></html>");

            emailService.prepareAndSend(user, "New Book Releases found", body.toString());

        });
    }


    @Transactional
    public void run() {

        List<Author> allAuthors = authorRepository.findAll();
        Map<User, List<Book>> userNewReleases = new HashMap<>();
        for (Author author : allAuthors) {

            List<iTunesBook> iTunesBooks = iTunesService.getAuthorBooks(author.getExternalId());

            Map<Long, Book> savedBookMap = new HashMap<>();
            for (Book book : author.getBooks()) {
                savedBookMap.put(book.getExternalId(), book);
            }
            for (iTunesBook iTunesBook : iTunesBooks) {

                if (iTunesBook.getTrackId() != null &&
                        savedBookMap.get(iTunesBook.getTrackId()) == null &&
                        iTunesBook.getArtistName().contains(author.getName())) {

                    Collection<User> usersList = userRepository.findByAuthors(author);

                    Book newBook = booksRepository.save(new Book(iTunesBook, author));

                    System.out.println("Found new book release!");
                    for (User user : usersList) {

                        user.getNewReleases().add(newBook);
                        userRepository.save(user);
                        if(!userNewReleases.containsKey(user)) {
                            userNewReleases.put(user, new ArrayList<>());
                        }
                        userNewReleases.get(user).add(newBook);

                    }
                }
            }
        }
        sendEmails(userNewReleases);
    }


}
