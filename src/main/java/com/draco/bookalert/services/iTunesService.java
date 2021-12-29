package com.draco.bookalert.services;

import com.draco.bookalert.models.itunes.iTunesAuthor;
import com.draco.bookalert.models.itunes.iTunesAuthorSearchResponse;
import com.draco.bookalert.models.itunes.iTunesBook;
import com.draco.bookalert.models.itunes.iTunesBookSearchResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
@Service
public class iTunesService {
    @Autowired
    RestTemplate restTemplate;

    public ArrayList<iTunesAuthor> getAuthorList(String search) {

        return restTemplate.getForEntity("https://itunes.apple.com/search?term=" + search + "&media=ebook&entity=ebookAuthor&attribute=authorTerm&limit=20", iTunesAuthorSearchResponse.class).getBody().getResults();

    }

    public ArrayList<iTunesBook> getTitleList(String search) {

        return restTemplate.getForEntity("https://itunes.apple.com/search?term=" + search + "&media=ebook&entity=ebook&limit=20", iTunesBookSearchResponse.class).getBody().getResults();

    }

    public ArrayList<iTunesBook> getAuthorBooks(int id) {
//        String encodedName = null;
//        try {
//            encodedName = URLEncoder.encode(int, StandardCharsets.UTF_8.toString()
//                    .replaceAll("\\%+", "%20")
//                    .replaceAll("\\%21", "!")
//                    .replaceAll("\\%27", "'")
//                    .replaceAll("\\%28", "(")
//                    .replaceAll("\\%29", ")")
//                    .replaceAll("\\%7E", "~"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        return restTemplate.getForEntity("https://itunes.apple.com/lookup?id=" + id + "&entity=ebook", iTunesBookSearchResponse.class).getBody().getResults();
    }
}
