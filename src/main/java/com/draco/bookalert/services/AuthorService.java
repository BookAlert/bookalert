package com.draco.bookalert.services;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Book;
import com.draco.bookalert.models.User;
import com.draco.bookalert.models.itunes.iTunesBook;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.BooksRepository;
import com.draco.bookalert.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private iTunesService iTunesService;

    @Autowired
    private UserRepository userRepository;

    //=====================  ADD AUTHOR FUNCTIONALITY
    public void addAuthor(Author author, String username) {
        User user = userRepository.findByUsername(username);
        Author existingAuthor = authorRepository.findAuthorByName(author.getName());
        if(existingAuthor == null)  {
            addNewAuthor(author, user);
        } else {
            addExistingAuthor(existingAuthor, user);
        }
    }


    private void addNewAuthor(Author author, User user) {
        Author savedAuthor = authorRepository.save(author);

        user.getAuthors().add(savedAuthor);
        userRepository.save(user);
        List<iTunesBook> iTunesBooks = iTunesService.getAuthorBooks(author.getName());
        for (iTunesBook iTunesBook : iTunesBooks) {
            Book book = new Book(iTunesBook, savedAuthor);
            booksRepository.save(book);
        }
    }

    private void addExistingAuthor(Author author, User user) {
        Author existingUserAuthor = null;
        for (Author a : user.getAuthors()) {
            if (a.getName().equals(author.getName())) {
                existingUserAuthor = a;
                break;
            }
        }
        if(existingUserAuthor == null) {
            user.getAuthors().add(author);
            userRepository.save(user);
        }
    }



  //=============== DELETE AUTHOR FUNCTIONALITY
    public void deleteAuthor(Author author, String username) {

        User user = userRepository.findByUsername(username);
        findToDeleteAuthor(author, user);

    }

    public void findToDeleteAuthor(Author author, User user) {
        if(user.getAuthors().contains(author)) {
            user.getAuthors().remove(author);
            userRepository.save(user);
        }


    }

    public Author findById(long id) {
        return authorRepository.findById(id);
    }



}
