package com.draco.bookalert.controllers;


import com.draco.bookalert.models.User;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

        Date timestamp = new Date();
        Timestamp ts = new Timestamp(timestamp.getTime());

//        Calendar calender = Calendar.getInstance();
//        calender.add(Calendar.DATE, +365);
//        Date toDate = calender.getTime();
//        boolean fromDate = ts.after(toDate);

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
