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
        List<Book> releaseBooks = new ArrayList<>();
        for (Author author : allAuthors) {

            List<iTunesBook> iTunesBooks = iTunesService.getAuthorBooks(author.getName());
            for (iTunesBook iTunesBook : iTunesBooks) {

                List<Book> existingBooks = booksRepository.findByAuthor_IdAndTitle(author.getId(), iTunesBook.getTrackName());

                if (existingBooks.isEmpty()) {
                    System.out.println(existingBooks);
                    Book newBook = booksRepository.save(new Book(iTunesBook, author));
                    Date now = new Date();

                    if (newBook.getRelease_date().before(now)) {
                            // new release detected
                            // add new record to the book_user table:
                            // user_id = currentUser’s
                            // book_id = newBook.getId()
                            // Get all the users that have a record in the author_user table, where the author_id = author.getId()
                            // ie: users that have this author saved in their library
                        releaseBooks.add(newBook);
                    }
                }
            }
        }
        System.out.println(releaseBooks);
    }




}
