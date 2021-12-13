package com.draco.bookalert.controllers;


import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Book;
import com.draco.bookalert.models.BookUser;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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



    ///=================================== ENDPOINT TO Profile PAGE

    @GetMapping("/profile")
    public String showProfile(Model model, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        if(user == null) {
            return "redirect:login";
        }
        model.addAttribute("authors", user.getAuthors());
        return "users/profile";
    }

    @GetMapping("/profile/saved-books")
    public String getSavedBooks(Model model, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        if(user == null) {
            return "redirect:login";
        }
        model.addAttribute("savedBooks",user.getSavedBooks());
        return "users/profile-saved-books";
    }

    @GetMapping("/profile/new-releases")
    public String getNewReleases(Model model, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        if(user == null) {
            return "redirect:login";
        }
        long now = System.currentTimeMillis();
        List<Book> newReleases = user.getNewReleases()
                .stream()
                .filter(book -> book.getRelease_date().getTime() <= now)
                .collect(Collectors.toList());

        model.addAttribute("newReleases", newReleases);
        return "users/profile-new-releases";
    }

    @GetMapping("/profile/upcoming-releases")
    public String getUpcomingReleases(Model model, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        if(user == null) {
            return "redirect:login";
        }
        long now = System.currentTimeMillis();
        List<Book> upcomingReleases = user.getNewReleases()
                .stream()
                .filter(book -> book.getRelease_date().getTime() > now)
                .collect(Collectors.toList());
        model.addAttribute("upcomingReleases", upcomingReleases);
        return "users/profile-upcoming-releases";
    }


    /// =================== ENDPOINT TO INDIVIDUAL AUTHOR PAGE
    @GetMapping("/authors/{id}")
    public String authorId(@PathVariable long id, Model authorModel, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Author author = authorRepository.getById(id);
        authorModel.addAttribute("author", authorRepository.getById(id));
        authorModel.addAttribute("books", booksRepository.findBookByAuthor(author));
        authorModel.addAttribute("purchasedBookIds", user.getPurchasedBooks().stream().map(Book::getId).collect(Collectors.toList()));
        return "authors/authors";
    }


//    @PostMapping("/authors/{id}")
//    public String authorPage(Model model) {
//        model.addAttribute("authors", authorRepository.findAll());
//        model.addAttribute("books", booksRepository.findAll());
////       model.addAttribute("releaseDate", new Date());
//        return "authors/authors";
//    }

    @GetMapping("/book/{id}")
    public String bookPage(@PathVariable long id, Model model) {
        model.addAttribute("book", booksRepository.getById(id));
        return "books/book";
    }
/// ============= UPCOMING RELEASES
    @ResponseBody
    @PostMapping("user/save-upcoming-title")
    public void saveUpcomingRelease(@RequestBody Book upcomingTitle, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Book book = booksRepository.getById(upcomingTitle.getId());
        user.getSavedBooks().add(book);
        user.getNewReleases().remove(book);
        userDao.save(user);
    }


    ///===================== DELETE FROM USERS LIST
    @ResponseBody
    @PostMapping("user/delete-title")
    public void deleteTitle(@RequestBody Book titleToDelete, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        user.getSavedBooks().remove(titleToDelete);
        userDao.save(user);
    }


    @ResponseBody
    @PostMapping("user/dismiss-new-release")
    public void dismiss(@RequestBody Book bookToRemove, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        System.out.println(user.getNewReleases().contains(bookToRemove));
        user.getNewReleases().remove(bookToRemove);
        userDao.save(user);
    }

    @ResponseBody
    @PostMapping("user/dismiss-all-new-releases")
    public void dismissAllNewReleases(Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        long now = System.currentTimeMillis();
        List<Book> newReleases = user.getNewReleases()
                .stream()
                .filter(book -> book.getRelease_date().getTime() <= now)
                .collect(Collectors.toList());
        user.getNewReleases().removeAll(newReleases);
        userDao.save(user);
    }


    @ResponseBody
    @PostMapping("/user/save-book")
    public void saveBook(@RequestBody Book bookToSave, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Book book = booksRepository.getById(bookToSave.getId());
        user.getSavedBooks().add(book);
        List<Book> updatedNewReleases = user.getNewReleases()
                .stream()
                .filter(newRelease -> newRelease.getId() != book.getId())
                .collect(Collectors.toList());
        user.setNewReleases(updatedNewReleases);
        userDao.save(user);
    }


    @ResponseBody
    @PostMapping("/user/mark-purchased")
    public void markPurchased(@RequestBody Book bookToMark, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Book book = booksRepository.getById(bookToMark.getId());
        user.getPurchasedBooks().add(book);
        user.getNewReleases().remove(book);
        userDao.save(user);
    }

    @ResponseBody
    @GetMapping("/user/authors")
    public List<String> authorList(Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        return user.getAuthors().stream().map(Author::getName).collect(Collectors.toList());
    }

    @ResponseBody
    @GetMapping("/user/getPurchased")
    public List<Book> getPurchased(Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        return user.getPurchasedBooks();
    }
}
