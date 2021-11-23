package com.draco.bookalert.repositories;

import com.draco.bookalert.models.Books;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
