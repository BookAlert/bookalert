package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Book;
import com.draco.bookalert.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class BooksController {
    @Autowired
    private BooksRepository booksRepository;


    @ResponseBody
    @RequestMapping(value = "/add-book", method = RequestMethod.POST)

    public void addTitle(@RequestBody Book book) {

        booksRepository.save(book);
    }



}
