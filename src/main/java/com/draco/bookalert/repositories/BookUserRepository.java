package com.draco.bookalert.repositories;

import com.draco.bookalert.models.BookUser;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookUserRepository extends JpaRepository<BookUser, Long> {

}
