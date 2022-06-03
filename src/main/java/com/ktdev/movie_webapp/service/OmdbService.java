package com.ktdev.movie_webapp.service;

import com.google.gson.Gson;
import com.ktdev.movie_webapp.pojo.Movie;
import com.ktdev.movie_webapp.pojo.Omdb;
import org.springframework.stereotype.Service;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OmdbService {

    private static final Logger LOGGER = Logger.getLogger(OmdbService.class.getName());

    public List<Movie> getMoviesFromOmdb(String title, String apikey){

        boolean response = true;
        List<Movie> movies = Collections.emptyList();
        int page = 1;
        String urlString = "https://www.omdbapi.com/?apikey="+apikey+"&s="+title+"&page="+page;

        while (response == true) {
            try {

                URL url = new URL(urlString);
                page += page;
                HttpURLConnection httpcon = (HttpURLConnection) url.openConnection();
                InputStreamReader reader = new InputStreamReader(httpcon.getInputStream());
                Gson gson = new Gson();
                Omdb omdb = gson.fromJson(reader, Omdb.class);

                if (omdb.getResponse().equalsIgnoreCase("true") == true){
                    movies.addAll(omdb.getMovies());
                } else {
                    response = false;
                }

            } catch (Exception e) {
                LOGGER.warning(e.getMessage());
            }
        }

        return movies;
    }
}
