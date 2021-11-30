package com.draco.bookalert.repositories;

import com.draco.bookalert.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BooksRepository extends JpaRepository<Book, Long> {

    Book findBookByTitleAndAuthor_Id(String trackName, long id);
}
