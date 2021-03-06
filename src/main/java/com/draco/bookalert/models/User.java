package com.draco.bookalert.models;


import org.hibernate.annotations.SQLInsert;
import org.hibernate.annotations.WhereJoinTable;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String first_name;

    @Column(nullable = false)
    private String last_name;

    @Column(nullable = false, length = 25, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column
    private boolean is_private;

    @ManyToMany
    @JoinTable(
            name="author_user",
            joinColumns ={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="author_id")}
    )
    private List<Author> authors;


    @WhereJoinTable(clause = "status_id = 1")
    @SQLInsert(sql = "insert into book_user (user_id, book_id, status_id) values (?, ?, 1)")
    @OrderBy(value = "release_date desc")
    @ManyToMany
    @JoinTable(
            name="book_user",
            joinColumns ={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="book_id")}
    )
    private List<Book> newReleases;

    @WhereJoinTable(clause = "status_id = 2")
    @SQLInsert(sql = "insert into book_user (user_id, book_id, status_id) values (?, ?, 2)")
    @ManyToMany
    @JoinTable(
            name="book_user",
            joinColumns ={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="book_id")}
    )
    private List<Book> savedBooks;

    @WhereJoinTable(clause = "status_id = 3")
    @SQLInsert(sql = "insert into book_user (user_id, book_id, status_id) values (?, ?, 3)")
    @ManyToMany
    @JoinTable(
            name="book_user",
            joinColumns ={@JoinColumn(name="user_id")},
            inverseJoinColumns = {@JoinColumn(name="book_id")}
    )
    private List<Book> purchasedBooks;

    @OneToMany(mappedBy = "user")
    private List<BookUser> bookUser;

    public User() {

    }

    // Our User class will be used for the authentication process (login/logout).
    // It will need the following constructor:

    public User(User copy) {
        id = copy.id; // This line is SUPER important! Many things won't work if it's absent
        email = copy.email;
        username = copy.username;
        password = copy.password;
    }
    public User(long id, String username, String email, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(long id, String first_name, String last_name, String username, String password, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isIs_private() {
        return is_private;
    }

    public void setIs_private(boolean is_private) {
        this.is_private = is_private;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public List<Book> getNewReleases() {
        return newReleases;
    }

    public List<Book> getPurchasedBooks() {
        return purchasedBooks;
    }

    public void setPurchasedBooks(List<Book> purchasedBooks) {
        this.purchasedBooks = purchasedBooks;
    }

    public List<Book> getSavedBooks() {
        return savedBooks;
    }

    public void setSavedBooks(List<Book> savedBooks) {
        this.savedBooks = savedBooks;
    }

    public void setNewReleases(List<Book> newReleases) {
        this.newReleases = newReleases;
    }
}
