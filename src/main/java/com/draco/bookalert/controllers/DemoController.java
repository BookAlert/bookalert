package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Book;
import com.draco.bookalert.models.User;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.services.AuthorService;
import com.draco.bookalert.services.RefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController

public class DemoController {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BooksRepository booksRepository;

    @Autowired
    RefreshService refreshService;

    @PostMapping("/fake-new-releases")
    public void fakeNewReleases() {
        List<Author> authors = authorRepository.findAll();
        for (int i = 0; i < authors.size() && i < 5; i++) {
            Author author = authors.get(i);
            if (author.getBooks() != null && !author.getBooks().isEmpty()) {
                Book book = author.getBooks().get(0);
                author.getBooks().remove(0);
                authorRepository.save(author);
                booksRepository.delete(book);

            }
        }
        refreshService.run();
    }
    }

