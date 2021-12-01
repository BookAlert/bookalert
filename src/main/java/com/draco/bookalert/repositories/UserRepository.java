package com.draco.bookalert.repositories;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    //Collection<User> findByAuthors(Author author);
}
