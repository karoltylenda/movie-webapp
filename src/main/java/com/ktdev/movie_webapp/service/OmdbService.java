package com.ktdev.movie_webapp.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OmdbService {

    private static final Logger LOGGER = Logger.getLogger(OmdbService.class.getName());

    public List<Movie> getMoviesFromOmdb(String title, String apikey){

        boolean response = true;
        List<Movie> movies = new ArrayList<>();
        RestTemplate restTemplate = new RestTemplate();
        int page = 1;
        String url = "https://www.omdbapi.com/?apikey="+apikey+"&s="+title+"&page="+page;

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>("body", headers);

        LOGGER.info(restTemplate.getForObject(url, Omdb.class).toString());

        return movies;
    }
}
