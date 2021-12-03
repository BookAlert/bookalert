package com.draco.bookalert.models;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "book_user")
public class BookUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private Status status;
}
