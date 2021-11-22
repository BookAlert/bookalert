package com.draco.bookalert.controllers;

 import com.draco.bookalert.models.Author;
 import com.draco.bookalert.models.itunes.iTunesAuthor;
 import com.draco.bookalert.models.itunes.iTunesAuthorSearchResponse;
 import com.draco.bookalert.repositories.AuthorRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.web.bind.annotation.*;

 import java.util.ArrayList;

 @RestController
 public class AuthorController {
     @Autowired
     private AuthorRepository authorRepository;


     @ResponseBody
     @RequestMapping(value = "/add-author", method = RequestMethod.POST)
     public void addAuthor(@RequestBody Author author) {
         authorRepository.save(author);
     }

     @GetMapping("/authors")
     public String authorpage() {
         return "/authors/authors";
     }

 }

