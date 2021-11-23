package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Books;
import com.draco.bookalert.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;


    @ResponseBody
    @RequestMapping(value = "/add-book", method = RequestMethod.POST)
    public void addTitle(@RequestBody Books book) {
        booksRepository.save(book);
    }



}
