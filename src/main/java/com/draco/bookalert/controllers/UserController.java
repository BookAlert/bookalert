package com.draco.bookalert.controllers;


import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Book;
import com.draco.bookalert.models.User;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.BookUserRepository;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.repositories.UserRepository;
import com.draco.bookalert.services.RefreshService;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    private UserRepository userDao;
    private PasswordEncoder passwordEncoder;
    private AuthorRepository authorRepository;
    private BooksRepository booksRepository;
    private RefreshService refreshService;
    private BookUserRepository bookUserRepository;

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
    public String showProfile(Model model, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        model.addAttribute("authors", user.getAuthors());
        model.addAttribute("newReleases", user.getNewReleases());
        model.addAttribute("upcomingReleases", booksRepository.findUpcomingReleases());
        model.addAttribute("savedBooks",user.getSavedBooks());
        // TODO model.addAttribute("recentReleases", booksRepository.findRecentReleases());
        return "users/profile";
    }

//    @PostMapping("/profile")
//    public String profilePage(Model model) {
//        model.addAttribute("authors", authorRepository.findAll());
//        model.addAttribute("books", booksRepository.findAll());
//        return "users/profile";
//    }

    /// =================== ENDPOINT TO INDIVIDUAL AUTHOR PAGE
    @GetMapping("/authors/{id}")
    public String authorId(@PathVariable long id, Model authorModel) {
        Author author = authorRepository.getById(id);
        authorModel.addAttribute("author", authorRepository.getById(id));
        authorModel.addAttribute("books", booksRepository.findBookByAuthor(author));
        return "authors/authors";
    }


    @PostMapping("/authors/{id}")
    public String authorPage(Model model) {
        model.addAttribute("authors", authorRepository.findAll());
        model.addAttribute("books", booksRepository.findAll());
        return "authors/authors";
    }

    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable long id, Model model) {
        model.addAttribute("book", booksRepository.getById(id));
        return "books/book";
    }


    @ResponseBody
    @PostMapping("/user/dismiss-new-release")
    public void dismiss(@RequestBody Book bookToRemove, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        user.getNewReleases().remove(bookToRemove);
        userDao.save(user);
    }

    @ResponseBody
    @PostMapping("/user/save-book")
    public void saveBook(@RequestBody Book bookToSave, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Book book = booksRepository.getById(bookToSave.getId());
        user.getSavedBooks().add(book);
        userDao.save(user);
    }

    @ResponseBody
    @PostMapping("/authors/save-book")
    public void saveAuthorBook(@RequestBody Book bookToSave, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Book book = booksRepository.getById(bookToSave.getId());
        user.getSavedBooks().add(book);
        userDao.save(user);
    }

    @ResponseBody
    @PostMapping("/user/mark-purchased")
    public void markPurchased(@RequestBody Book bookToMark, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Book book = booksRepository.getById(bookToMark.getId());
        user.getPurchasedBooks().add(book);
        userDao.save(user);
    }




}
