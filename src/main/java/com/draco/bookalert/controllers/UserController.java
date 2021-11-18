package com.draco.bookalert.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class UserController {

    @GetMapping("/profile")
    public String showProfile() {
        return "users/profile";
    }

    @RequestMapping(value = "img/{imageName}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageName") String imageName) throws IOException {

        File serverFile = new File("/static/img/" + imageName + ".jpg");

        return Files.readAllBytes(serverFile.toPath());
    }
}
