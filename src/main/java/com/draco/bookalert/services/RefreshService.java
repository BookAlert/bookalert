package com.draco.bookalert.services;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Book;
import com.draco.bookalert.models.itunes.iTunesBook;
import com.draco.bookalert.repositories.AuthorRepository;
import com.draco.bookalert.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class RefreshService {

    @Autowired
    private AuthorService authorService;
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private iTunesService iTunesService;

    @Autowired
    private BooksRepository booksRepository;



    public void run() {
        Collection<Author> allAuthors = authorRepository.findAll();
        for (Author author : allAuthors) {
           List<iTunesBook> iTunesBooks = iTunesService.getAuthorBooks(author.getName());
            for (iTunesBook iTunesBook : iTunesBooks) {
                Book existingBook = booksRepository.findBookByTitleAndAuthor_Id(iTunesBook.getTrackName(), author.getId() );
                if (existingBook == null) {
                   Book newBook = booksRepository.save(new Book(iTunesBook, author));
//                    if (newBook.getReleaseDate().isWithinTheLastMonth()) {
//                        // new release detected
//                        // add new record to the book_user table:
//                        // user_id = currentUser's
//                        // book_id = newBook.getId()
//                        // Get all the users that have a record in the author_user table, where the author_id = author.getId()
//                        // ie: users that have this author saved in their library
//                        Collection<User> users = userService.getByAuthor(author);
//                        for (User user : users) {
//                            user.newReleases.add(newBook);
//                            userRepository.save(user);
//                        }
//                   }
               }
           }
        }
   }
}
