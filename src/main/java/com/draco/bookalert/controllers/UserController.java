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
import java.util.List;

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
        if(user == null) {
            return "redirect:login";
        }
        model.addAttribute("authors", user.getAuthors());
        model.addAttribute("newReleases", user.getNewReleases());
        List<Book> allNewReleases = booksRepository.findUpcomingReleases();
        List<Book> userNewReleases = new ArrayList<>();
        for(Book book : allNewReleases) {
            Author author = book.getAuthor();
            if(user.getAuthors().contains(author)) {
                userNewReleases.add(book);
            }
        }
        model.addAttribute("upcomingReleases", userNewReleases);
        model.addAttribute("savedBooks",user.getSavedBooks());

        // TODO model.addAttribute("recentReleases", booksRepository.findRecentReleases());
        return "users/profile";
    }


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
/// ============= UPCOMING RELEASES
    @ResponseBody
    @PostMapping("user/save-upcoming-title")
    public void saveUpcomingRelease(@RequestBody Book upcomingTitle, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Book book = booksRepository.getById(upcomingTitle.getId());
        user.getSavedBooks().add(book);
        List<Book> books = new ArrayList<>();
        for(Book upcomingBook : user.getUpcomingBooks()) {
            if(!upcomingBook.equals(upcomingTitle)) {
               books.add(upcomingBook);
            }

        }
        user.setUpcomingBooks(books);
        userDao.save(user);

    }

    @ResponseBody
    @PostMapping("/user/dismiss-upcoming-release")
    public void dismissUpcomingRelease(@RequestBody Book bookToDismiss, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        user.getUpcomingBooks().remove(bookToDismiss);

        userDao.save(user);
    }


    @ResponseBody
    @PostMapping("/user/purchased-upcoming")
    public void purchasedStatusUpcoming(@RequestBody Book upcomingPurchase, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Book book = booksRepository.getById(upcomingPurchase.getId());
        user.getPurchasedBooks().add(book);
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
    public void dismissAllNewReleases(@RequestBody Book books, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        List<Book> booksToRemove = new ArrayList<>();
        user.setNewReleases(new ArrayList<>());
        user.getNewReleases().removeAll(booksToRemove);
        userDao.save(user);
    }


    @ResponseBody
    @PostMapping("/user/save-book")
    public void saveBook(@RequestBody Book bookToSave, Authentication authentication) {
        User user = userDao.findByUsername(authentication.getName());
        Book book = booksRepository.getById(bookToSave.getId());
        user.getSavedBooks().add(book);
        List<Book> books = new ArrayList<>();
        for(Book book2 : user.getNewReleases()) {
            if (!book2.equals(bookToSave)) {
                books.add(book2);
            }
        }
        user.setNewReleases(books);
        user.getNewReleases().remove(book);
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
