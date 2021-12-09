package com.draco.bookalert.controllers;


import com.draco.bookalert.models.User;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @Autowired
    private BooksRepository booksRepository;

    ////===================================  ENDPOINT FOR HOME PAGE  ====
    @GetMapping("/")
    public String landingPage(Model viewModel) {
        viewModel.addAttribute("books", booksRepository.findAll());
        return "users/home";
    }



    ///==================================  ENDPOINT TO SEARCH PAGE
    @GetMapping("/search")
    public String searchPage() {
        return "users/search";
    }


    ///================================== ENDPOINT TO ABOUT PAGE
    @GetMapping("/about")
    public String aboutPage() {
        return "users/about";
    }

    @GetMapping("/authors")
    public String authorPage() {
        return "authors/authors";
    }




    //======================  ENDPOINT TO BOOK(ID) VIEW
    @GetMapping("/book")
    public String viewBook() {
        return "books/book";
    }

}
