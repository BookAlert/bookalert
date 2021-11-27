package com.draco.bookalert.controllers;

 import com.draco.bookalert.models.Author;
 import com.draco.bookalert.models.itunes.iTunesAuthor;
 import com.draco.bookalert.models.itunes.iTunesAuthorSearchResponse;
 import com.draco.bookalert.repositories.AuthorRepository;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.*;
 import org.springframework.web.servlet.mvc.support.RedirectAttributes;

 import java.util.ArrayList;
 import java.util.List;

@RestController
 public class AuthorController {
     @Autowired
     private AuthorRepository authorRepository;

     @RequestMapping(value = "/add-author", method = RequestMethod.POST)
     public void addAuthor(@RequestBody Author author) {
         List<Author> a = authorRepository.findAuthorByName(author.getName());
         if(a.size() == 0)  {
            authorRepository.save(author);

         }
    }

}




