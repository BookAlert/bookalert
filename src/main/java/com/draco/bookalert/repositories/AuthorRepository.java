package com.draco.bookalert.repositories;

import com.draco.bookalert.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByName(String name);

    boolean existsByName(String name);

}
