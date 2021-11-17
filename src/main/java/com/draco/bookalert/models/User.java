package com.draco.bookalert.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String first_name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String last_name;

    @Column(nullable = false, length = 25, unique = true)
    private String username;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String password;

    @Column(nullable = false, columnDefinition = "TEXT", unique = true)
    private String email;

    @Column(nullable = false)
    private boolean is_private;


    @OneToMany( mappedBy = "users")
    private List<Author> authors;



    public User() {

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
}
