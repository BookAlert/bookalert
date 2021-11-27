package com.draco.bookalert.controllers;

import com.draco.bookalert.models.Author;
import com.draco.bookalert.models.itunes.iTunesAuthor;
import com.draco.bookalert.models.itunes.iTunesAuthorSearchResponse;
import com.draco.bookalert.models.itunes.iTunesBook;
import com.draco.bookalert.models.itunes.iTunesBookSearchResponse;
import com.draco.bookalert.services.iTunesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;

@RestController
public class ConsumeWebServiceController {
    @Autowired
    private iTunesService iTunesService;

    @ResponseBody
    @RequestMapping(value = "/author-suggestions")
    public ArrayList<iTunesAuthor> getProductList(@RequestParam String search) {

        return iTunesService.getProductList(search);
    }

    @ResponseBody
    @RequestMapping(value = "/book-suggestions")
    public ArrayList<iTunesBook> getTitleList(@RequestParam String search) {

        return iTunesService.getTitleList(search);

    }

}

