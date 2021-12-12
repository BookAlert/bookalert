package com.draco.bookalert.repositories;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;


public interface BooksRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthor_IdAndTitle(long id, String trackName);
    List<Book> findBookByAuthor(Author author);
    List<Book> findBookByAuthorId(long author);

//    @Query("select s from Book s where s.release_date > current_timestamp - DAY(60)")
//    List<Book> findRecentReleases();

    Book findByExternalId(long id);
    Book findByTitle(String title);
}
