package com.draco.bookalert.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

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

//    @RequestMapping("/{movieId}")
//    public Movie getMovieInfo(@PathVariable("movieId") String movieId) {
//        MovieSummary movieSummary = restTemplate.getForObject("https://api.themoviedb.org/3/movie/" + movieId + "?api_key=" +  apiKey, MovieSummary.class);
//        return new Movie(movieId, movieSummary.getTitle(), movieSummary.getOverview());
//
//    }
}

