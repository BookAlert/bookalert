package com.draco.bookalert.models.itunes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@JsonIgnoreProperties(ignoreUnknown = true)
public class iTunesAuthorSearchResponse implements Serializable {
    private ArrayList<iTunesAuthor> results = new ArrayList<>();

    public ArrayList<iTunesAuthor> getResults() {
        return results;
    }

    public void setResults(ArrayList<iTunesAuthor> results) {
        this.results = results;
    }
}

