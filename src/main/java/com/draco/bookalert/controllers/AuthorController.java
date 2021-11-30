package com.draco.bookalert.controllers;

 import com.draco.bookalert.models.Author;

 import com.draco.bookalert.services.AuthorService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.scheduling.annotation.Scheduled;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContext;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.*;

@RestController
 public class AuthorController {
     @Autowired
     private AuthorService authorService;

     @RequestMapping(value = "/add-author", method = RequestMethod.POST)
     public void addAuthor(@RequestBody Author author, Authentication authentication) {
        String username = authentication.getName();

        authorService.addAuthor(author, username);
     }



}




