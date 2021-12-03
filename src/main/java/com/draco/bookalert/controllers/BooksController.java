package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Book;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private UserRepository userRepository;


    @ResponseBody
    @RequestMapping(value = "/add-book", method = RequestMethod.POST)

    public void addTitle(@RequestBody Book book, Authentication authentication) {
        Book newBook = booksRepository.findByTitle(book.getTitle());
        String username = authentication.getName();
        if(newBook == null) {
            booksRepository.save(book);
        }
        userRepository.findByUsername(username);




    }



}
