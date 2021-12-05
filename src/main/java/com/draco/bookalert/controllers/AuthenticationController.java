package com.draco.bookalert.controllers;


import com.draco.bookalert.services.RefreshService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthenticationController {
//    The Login Page
//      Now we need a controller and a template with the login form.
//      We will define our login form to submit a POST request to /login and Spring will take care of handling that request.

    @Autowired
    private RefreshService refreshService;

    @GetMapping("/login")
    public String showLoginForm() {
        refreshService.run();
        return "users/login";
    }


}
