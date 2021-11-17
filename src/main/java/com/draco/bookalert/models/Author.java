package com.draco.bookalert.models;

import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, columnDefinition = "TEXT")
    private String name;

    @OneToMany(mappedBy = "authors")
    private List<Books> books;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User users;

    public Author() {

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

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> books) {
        this.books = books;
    }
}
