package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.repositories.AuthorRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AuthorController {

    private final AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }


    @GetMapping("/searchTest")
    public String createAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "users/searchTest";
    }

    @PostMapping("/users/searchTest")
    public String insertAuthor(@ModelAttribute Author author, Model model) {
        model.addAttribute("author", author);
        authorRepository.save(author);
        return "redirect:/searchTest";
    }
}

