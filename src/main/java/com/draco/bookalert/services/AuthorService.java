package com.draco.bookalert.services;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Book;
import com.draco.bookalert.models.itunes.iTunesBook;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private iTunesService iTunesService;

    public void addAuthor(Author author) {
        List<Author> a = authorRepository.findAuthorByName(author.getName());
        if(a.size() == 0)  {
            Author savedAuthor = authorRepository.save(author);
            List<iTunesBook> iTunesBooks = iTunesService.getAuthorBooks(author.getName());
            for (iTunesBook iTunesBook : iTunesBooks) {
                Book book = new Book(iTunesBook, savedAuthor);
                booksRepository.save(book);
            }
        }
    }
}
