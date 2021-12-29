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

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private iTunesService iTunesService;

    @Autowired
    private UserRepository userRepository;

    //=====================  ADD AUTHOR FUNCTIONALITY
    public void addAuthor(Author author, String username) {
        User user = userRepository.findByUsername(username);
        Author existingAuthor = authorRepository.findAuthorByName(author.getName());
        if(existingAuthor == null)  {
            addNewAuthor(author, user);
        } else {
            addExistingAuthor(existingAuthor, user);
        }
    }


    private void addNewAuthor(Author author, User user) {
        Author savedAuthor = authorRepository.save(author);

        List<Book> books = new ArrayList<>();
        List<iTunesBook> iTunesBooks = iTunesService.getAuthorBooks(author.getExternalId());
        for (iTunesBook iTunesBook : iTunesBooks) {
            if(iTunesBook.getTrackId() == null) {
                continue;
            }
            if (iTunesBook.getArtistName().contains(author.getName())) {
                Book book = new Book(iTunesBook, savedAuthor);
                book = booksRepository.save(book);
                books.add(book);
            }
        }
        savedAuthor.setBooks(books);
        addAuthorToUser(user, savedAuthor);
    }

    private void addExistingAuthor(Author author, User user) {
        Author existingUserAuthor = null;
        for (Author a : user.getAuthors()) {
            if (a.getName().equals(author.getName())) {
                existingUserAuthor = a;
                break;
            }
        }
        if(existingUserAuthor == null) {
            addAuthorToUser(user, author);
        }
    }

    private void addAuthorToUser(User user, Author author) {
        user.getAuthors().add(author);
        userRepository.save(user);

        long now = System.currentTimeMillis();
        if (user.getNewReleases() == null){
            user.setNewReleases(new ArrayList<>());
        }
        author.getBooks().forEach(book -> {
            if (book.getRelease_date().getTime() > now){
                user.getNewReleases().add(book);
            }
        });
        userRepository.save(user);
    }



  //=============== DELETE AUTHOR FUNCTIONALITY
    public void deleteAuthor(Author author, String username) {

        User user = userRepository.findByUsername(username);
        findToDeleteAuthor(author, user);

    }

    public void findToDeleteAuthor(Author author, User user) {
        if(user.getAuthors().contains(author)) {
            user.getAuthors().remove(author);
            userRepository.save(user);
        }


    }

    public Author findById(long id) {
        return authorRepository.findById(id);
    }



}
