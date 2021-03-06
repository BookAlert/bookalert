package com.draco.bookalert.repositories;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.User;
import org.hibernate.annotations.SQLInsert;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    Collection<User> findByAuthors(Author author);

}
