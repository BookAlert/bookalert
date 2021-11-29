package com.draco.bookalert.models;

import com.draco.bookalert.models.itunes.iTunesBook;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column
    private Timestamp release_date;


    @ManyToMany
    @JoinTable(
            name = "book_genre",
            joinColumns = { @JoinColumn(name = "book_id")},
            inverseJoinColumns = { @JoinColumn(name = "genre_id")}
    )
    private List<Genre> genres;

    @Column (name = "itunes_url")
    private String itunes_url;

    @Column(name = "artwork_url")
    private String artwork_url;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @ManyToMany(mappedBy = "books")
    private List<User> users;

    public Book() {

    }

    public Book(long id, String title, String description, Timestamp release_date, List<Genre> genres, String itunes_url, String artwork_url, Author author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.release_date = release_date;
        this.genres = genres;
        this.itunes_url = itunes_url;
        this.artwork_url = artwork_url;
        this.author = author;
    }

    public Book(iTunesBook book, Author author) {
        this.title = book.getTrackName();
        this.description = book.getDescription();
        this.release_date = book.getReleaseDate();
//        this.genres = null;
        this.itunes_url = book.getTrackViewUrl();
        this.artwork_url = book.getArtworkUrl100();
        this.author = author;
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

    public Timestamp getRelease_date() {
        return release_date;
    }

    public void setRelease_date(Timestamp release_date) {
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author authors) {
        this.author = authors;
    }
}
