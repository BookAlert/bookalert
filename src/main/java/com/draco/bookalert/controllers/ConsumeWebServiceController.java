package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.itunes.iTunesAuthor;
import com.draco.bookalert.models.itunes.iTunesAuthorSearchResponse;
import com.draco.bookalert.models.itunes.iTunesBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class ConsumeWebServiceController {
    @Autowired
    RestTemplate restTemplate;

    @ResponseBody
    @RequestMapping(value = "/author-suggestions")
    public ArrayList<iTunesAuthor> getProductList(@RequestParam String search) {

        return restTemplate.getForEntity("http://itunes.apple.com/search?term=" + search + "&media=ebook&entity=ebookAuthor&attribute=authorTerm&limit=16", iTunesAuthorSearchResponse.class).getBody().getResults();

    }


    //    @RequestMapping("/{movieId}")
//    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
//        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
//        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
//
//    }
}

