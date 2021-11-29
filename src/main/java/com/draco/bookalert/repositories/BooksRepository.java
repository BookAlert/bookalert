package com.draco.bookalert.repositories;

import com.draco.bookalert.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Timestamp;
import java.util.Date;
import java.util.List;

public interface BooksRepository extends JpaRepository<Book, Long> {

    List<Book> findByStartDateAfter(Timestamp date);

}
