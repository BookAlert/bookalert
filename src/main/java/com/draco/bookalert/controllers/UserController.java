package com.draco.bookalert.controllers;


import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.User;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Date;

@Controller
public class UserController {

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;

    private AuthorRepository authorRepository;

    private BooksRepository booksRepository;

    public UserController(UserRepository userDao, PasswordEncoder passwordEncoder, AuthorRepository authorRepository, BooksRepository booksRepository) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
        this.authorRepository = authorRepository;
        this.booksRepository = booksRepository;
    }

    /// ================================== ENDPOINT TO SIGN UP PAGE
    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        userDao.save(user);
        return "redirect:/login";
    }

    ///=================================== ENDPOINT TO LOGIN PAGE

    @GetMapping("/profile")
    public String showProfile(Model model, Timestamp date) {
        Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
        booksRepository.findByStartDateAfter();
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("books", booksRepository.findAll());
        return "users/profile";
    }

    @GetMapping("/authors/{id}")
    public String authorId(@PathVariable long id, Model authorModel) {
        authorModel.addAttribute("author", authorRepository.getById(id));
        return "authors/authors";
    }


    @PostMapping("/profile")
    public String profilePage(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("books", booksRepository.findAll());
        return "users/profile";
    }


    @GetMapping("/test")
    @ResponseBody
    public String deleteUser() {
        userDao.deleteById(5L);
        return "test";
    }


}
