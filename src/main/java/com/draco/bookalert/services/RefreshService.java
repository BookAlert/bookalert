package com.draco.bookalert.services;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Book;
import com.draco.bookalert.models.User;
import com.draco.bookalert.models.itunes.iTunesBook;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Transactional
    public void run() {

        List<Author> allAuthors = authorRepository.findAll();

        for (Author author : allAuthors) {

            List<iTunesBook> iTunesBooks = iTunesService.getAuthorBooks(author.getName());


            Map<Long, Book> savedBookMap = new HashMap<>();
            for (Book book : author.getBooks()) {
                savedBookMap.put(book.getExternalId(), book);
            }
            for (iTunesBook iTunesBook : iTunesBooks) {

                if (savedBookMap.get(iTunesBook.getTrackId()) == null && iTunesBook.getArtistName().contains(author.getName())) {

                    Collection<User> usersList = userRepository.findByAuthors(author);

                    Book newBook = booksRepository.save(new Book(iTunesBook, author));
                    System.out.println("Found new book release!");
                    for (User user : usersList) {

                        user.getNewReleases().add(newBook);
                        userRepository.save(user);
                    }
//                    Date now = new Date();
//                    System.out.println(now);
//                    if (newBook.getRelease_date().after(now)) {
//                            // new release detected
//                            // add new record to the book_user table:
//                            // user_id = currentUser’s
//                            // book_id = newBook.getId()
//                            // Get all the users that have a record in the author_user table, where the author_id = author.getId()
//                            // ie: users that have this author saved in their library
//
//                    }
                }
            }
        }

    }


}
