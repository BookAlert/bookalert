package com.draco.bookalert.controllers;

// <<<<<<< javier-guerra
// import com.draco.bookalert.models.Author;
// import com.draco.bookalert.repositories.AuthorRepository;
// import org.springframework.stereotype.Controller;
// import org.springframework.ui.Model;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.ModelAttribute;
// import org.springframework.web.bind.annotation.PostMapping;

// import java.util.List;

// @Controller
// public class AuthorController {

//     private final AuthorRepository authorRepository;

//     public AuthorController(AuthorRepository authorRepository) {
//         this.authorRepository = authorRepository;
//     }


//     @GetMapping("/searchTest")
//     public String createAuthor(Model model) {
//         model.addAttribute("author", new Author());
//         return "users/searchTest";
//     }

//     @PostMapping("/users/searchTest")
//     public String insertAuthor(@ModelAttribute Author author, Model model) {
//         model.addAttribute("author", author);
//         authorRepository.save(author);
//         return "redirect:/searchTest";
//     }
// }

// =======

// import com.draco.bookalert.models.Author;
// import com.draco.bookalert.models.itunes.iTunesAuthor;
// import com.draco.bookalert.models.itunes.iTunesAuthorSearchResponse;
// import com.draco.bookalert.repositories.AuthorRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.web.bind.annotation.*;

// import java.util.ArrayList;

// @RestController
// public class AuthorController {
//     @Autowired
//     private AuthorRepository authorRepository;


//     @ResponseBody
//     @RequestMapping(value = "/add-author", method = RequestMethod.POST)
//     public void addAuthor(@RequestBody Author author) {
//         authorRepository.save(author);
//     }


// }
// >>>>>>> main
