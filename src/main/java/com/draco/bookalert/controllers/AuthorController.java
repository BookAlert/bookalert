package com.draco.bookalert.controllers;

 import com.draco.bookalert.models.Author;

 import com.draco.bookalert.models.Book;
 import com.draco.bookalert.models.User;
 import com.draco.bookalert.repositories.BooksRepository;
 import com.draco.bookalert.repositories.UserRepository;
 import com.draco.bookalert.services.AuthorService;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.scheduling.annotation.Scheduled;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContext;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.*;

 import java.util.List;

@Controller
 public class AuthorController {
     @Autowired
     private AuthorService authorService;

     @Autowired
     private BooksRepository booksRepository;

     @Autowired
     private UserRepository userDao;



     @ResponseBody
     @RequestMapping(value = "/add-author", method = RequestMethod.POST)
     public void addAuthor(@RequestBody Author author, Authentication authentication) {
        String username = authentication.getName();
         authorService.addAuthor(author, username);
     }

     @RequestMapping(value = "/authors/{id}/delete", method = RequestMethod.POST)
    public String  deletedAuthor(@PathVariable long id, Authentication authentication) {
       String username = authentication.getName();
         Author authorToDelete = authorService.findById(id);
         if(authorToDelete != null) {
             authorService.deleteAuthor(authorToDelete, username);
         }
         return "redirect:/profile";
     }

    @ResponseBody
    @GetMapping("/authors/user-authors")
    public List<Author> userAuthors(@RequestBody Author author, Authentication authentication) {
         User user = userDao.findByUsername(authentication.getName());
         List<Author> usersAuthorList = user.getAuthors();
         return usersAuthorList;
    }

}




