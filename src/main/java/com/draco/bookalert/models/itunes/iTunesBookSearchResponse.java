package com.draco.bookalert.models.itunes;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class iTunesBookSearchResponse implements Serializable {

    private ArrayList<iTunesBook> results = new ArrayList<>();

    public ArrayList<iTunesBook> getResults() {
        return results;
    }

    public void setResults(ArrayList<iTunesBook> results) {
        this.results = results;
    }


}
