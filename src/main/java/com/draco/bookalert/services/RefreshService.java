package com.draco.bookalert.services;

import com.draco.bookalert.models.Author;
import org.springframework.stereotype.Service;

@Service
public class RefreshService {

//    public void run() {
//        Collection<Author> allAuthors = authorService.getAllAuthors();
//        for (Author author : allAuthors) {
//            List<iTunesBook> iTunesBooks = iTunesService.getAuthorBooks(author.getName());
//            for (iTunesBook iTunesBook : iTunesBooks) {
//                Book existingBook = bookService.getByAuthorIdAndBookTitle(author.getId(), iTunesBook.getTrackName());
//                if (existingBook == null) {
//                    Book newBook = bookRepository.save(new Book(iTunesBook, author));
////                    if (newBook.getReleaseDate().isWithinTheLastMonth()) {
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
////                    }
//                }
//            }
//        }
//    }
}
