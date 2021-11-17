package com.draco.bookalert.controllers;


import com.draco.bookalert.models.User;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    private UserRepository userDao;

    public HomeController(UserRepository userDao) {
        this.userDao = userDao;
    }

    ////===================================  ENDPOINT FOR HOME PAGE  ====
    @GetMapping("/")
    public String landingPage() {
        return "/users/home";
    }


    /// ================================== ENDPOINT TO SIGN UP PAGE
    @GetMapping("/sign-up")
    public String signUpPage(Model model) {
        model.addAttribute("user", new User());
        return "users/sign-up";
    }



}
