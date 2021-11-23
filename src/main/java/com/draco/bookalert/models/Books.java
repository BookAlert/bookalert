package com.draco.bookalert.models;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Time;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "books")
public class Books {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private String release_date;


    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = { @JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "genre_id")}
    )
    private List<Genre> genres;

    @Column(nullable = false)
    private String itunes_url;

    @Column(nullable = false)
    private String artwork_url;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author authors;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    public Books() {

    }

    public Books(long id, String title, String description, String release_date, List<Genre> genres, String itunes_url, String artwork_url, Author authors) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.genres = genres;
        this.itunes_url = itunes_url;
        this.artwork_url = artwork_url;
        this.authors = authors;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public String getItunes_url() {
        return itunes_url;
    }

    public void setItunes_url(String itunes_url) {
        this.itunes_url = itunes_url;
    }

    public String getArtwork_url() {
        return artwork_url;
    }

    public void setArtwork_url(String artwork_url) {
        this.artwork_url = artwork_url;
    }

    public Author getAuthors() {
        return authors;
    }

    public void setAuthors(Author authors) {
        this.authors = authors;
    }
}
