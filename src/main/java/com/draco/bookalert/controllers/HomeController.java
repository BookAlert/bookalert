package com.draco.bookalert.controllers;


import com.draco.bookalert.models.User;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {


    ////===================================  ENDPOINT FOR HOME PAGE  ====
    @GetMapping("/")
    public String landingPage() {
        return "/users/home";
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

//    @GetMapping("/profile")
//    public String profilePage() {
//        return "users/profile";
//    }
}
