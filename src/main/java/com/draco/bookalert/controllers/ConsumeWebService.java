package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.Books;
import com.draco.bookalert.models.iTunesAuthorSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.awt.print.Book;
import java.util.Arrays;

@RestController
public class ConsumeWebService {
    @Autowired
    RestTemplate restTemplate;

    @RequestMapping(value = "/bookinfo")
    public String getProductList() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);

        return restTemplate.exchange("http://itunes.apple.com/search?term=anthony+robbins&media=ebook&entity=ebook&limit=500&attribute=authorTerm", HttpMethod.GET, entity, String.class).getBody();
    }

    @RequestMapping("/{author}")
    public Author getAuthorInfo(@PathVariable("author") String author) {
        iTunesAuthorSearchResponse itunesResponse = restTemplate.getForObject("http://itunes.apple.com/search?term=" + author + "&lang=en_us&media=ebook&entity=ebook&limit=500&attribute=authorTerm", iTunesAuthorSearchResponse.class);
        return new Author(itunesResponse.getTrackName());
    }
//    @RequestMapping("/{movieId}")
//    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
//        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
//        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
//
//    }
}

