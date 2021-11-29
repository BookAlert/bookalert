package com.draco.bookalert.models;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "author")
    private List<Book> books;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User users;

//    @ManyToMany(mappedBy = "authors")
//    private List<User> users;

    public Author() {

    }

    public Author(List<Book> books) {
        this.books = books;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
