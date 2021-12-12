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
import java.util.List;

@RestController
public class ConsumeWebServiceController {
    @Autowired
    private iTunesService iTunesService;

    @Autowired
    private UserRepository userDao;

    @Autowired
    private AuthorRepository authorRepository;

    @ResponseBody
    @RequestMapping(value = "/author-suggestions")
    public ArrayList<iTunesAuthor> getAuthorList(@RequestParam String search, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        List<Author> userAuthors = user.getAuthors();
        ArrayList<iTunesAuthor> allAuthors = iTunesService.getAuthorList(search);
        for (iTunesAuthor author : allAuthors) {
            if(userAuthors.contains(author)) {

            }
        }
        return iTunesService.getAuthorList(search);
    }

    @ResponseBody
    @RequestMapping(value = "/book-suggestions")
    public ArrayList<iTunesBook> getTitleList(@RequestParam String search) {

        return iTunesService.getTitleList(search);

    }

}

