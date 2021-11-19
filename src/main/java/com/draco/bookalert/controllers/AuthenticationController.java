package com.draco.bookalert.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
//    The Login Page
//      Now we need a controller and a template with the login form.
//      We will define our login form to submit a POST request to /login and Spring will take care of handling that request.

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }


}
