package com.draco.bookalert.repositories;

import com.draco.bookalert.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book, Long> {
}
