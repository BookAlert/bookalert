package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Book;
import com.draco.bookalert.models.BookUser;
import com.draco.bookalert.models.Status;
import com.draco.bookalert.models.User;
import com.draco.bookalert.repositories.BookUserRepository;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.repositories.StatusRepository;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class BooksController {
   // @Autowired
    private BooksRepository booksRepository;

   // @Autowired
    private UserRepository userRepository;

   // @Autowired
    private BookUserRepository bookUserRepository;

    private StatusRepository statusRepository;

    public BooksController(BooksRepository booksRepository, UserRepository userRepository, BookUserRepository bookUserRepository, StatusRepository statusRepository) {
        this.booksRepository = booksRepository;
        this.userRepository = userRepository;
        this.bookUserRepository = bookUserRepository;
        this.statusRepository = statusRepository;
    }

    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public void addTitle(@RequestBody Book book, Authentication authentication) {
        Book newBook = booksRepository.findByTitle(book.getTitle());
        String username = authentication.getName();
        Status status = new Status();
        status.setId(2);
        statusRepository.save(status);
        if(newBook == null) {
            booksRepository.save(book);
            newBook = book;
        }

        BookUser bookUser = new BookUser();
        User user = userRepository.findByUsername(username);
        bookUser.setStatus(status);
        bookUser.setUser(user);
        bookUser.setBook(newBook);
        bookUserRepository.save(bookUser);


    }

    @RequestMapping(value = "/dismiss", method = RequestMethod.POST)
    public void dismiss(@RequestBody Book book, Authentication authentication) {
        Book newBook = booksRepository.findByTitle(book.getTitle());
        String username = authentication.getName();
        Status status = new Status();
        status.setId(2);
        statusRepository.save(status);
        if(newBook == null) {
            booksRepository.save(book);
            newBook = book;
        }

        BookUser bookUser = new BookUser();
        User user = userRepository.findByUsername(username);
        bookUser.setStatus(status);
        bookUser.setUser(user);
        bookUser.setBook(newBook);
        bookUserRepository.save(bookUser);


    }

}
