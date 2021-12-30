package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.User;
import com.draco.bookalert.models.itunes.iTunesAuthor;
import com.draco.bookalert.models.itunes.iTunesBook;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.UserRepository;
import com.draco.bookalert.services.iTunesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@RestController
public class ConsumeWebServiceController {
    @Autowired
    private iTunesService iTunesService;

    @ResponseBody
    @RequestMapping(value = "/author-suggestions")
    public ArrayList<iTunesAuthor> getAuthorList(@RequestParam String search) {
        ArrayList<iTunesAuthor> allAuthors = iTunesService.getAuthorList(search);
        if (!search.toLowerCase().equals(search)) {
            ArrayList<iTunesAuthor> allAuthorsLowercase = iTunesService.getAuthorList(search.toLowerCase());
            allAuthors.addAll(allAuthorsLowercase);
        }
        return new ArrayList<>(new HashSet<>(allAuthors));
    }

    @ResponseBody
    @RequestMapping(value = "/book-suggestions")
    public ArrayList<iTunesBook> getTitleList(@RequestParam String search) {

        return iTunesService.getTitleList(search);

    }

}

