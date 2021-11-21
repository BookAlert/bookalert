package com.draco.bookalert.models.itunes;

import com.draco.bookalert.models.Books;
import com.draco.bookalert.models.Genre;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class iTunesAuthor {

    private String artistName;

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }
}
