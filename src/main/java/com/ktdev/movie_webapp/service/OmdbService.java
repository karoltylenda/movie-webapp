package com.ktdev.movie_webapp.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ktdev.movie_webapp.omdb.OmdbMovie;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class OmdbService {

    private static final Logger LOGGER = Logger.getLogger(OmdbService.class.getName());

    public List<OmdbMovie> getMoviesFromOmdb(String title, String apikey){

        boolean response = true;
        List<OmdbMovie> movies = new ArrayList<>();

        Integer page = 1;
        String string = "https://www.omdbapi.com/?apikey="+apikey+"&s="+title+"&page="+page;

        while (response){
            try {
                URL url = new URL(string);
                String responseFromUrl = get(url).get("Response").asText();

                if (responseFromUrl.equalsIgnoreCase("False")) {
                    response = false;
                } else {
                    page++;
                    get(url).get("Search").forEach(jsonNode -> {
                        movies.add(setMovie(jsonNode));
                    });
                }

            } catch (Exception e) {
                e.printStackTrace();
                LOGGER.info(e.getMessage());
                response = false;
            }
        }

        movies.forEach(omdbMovie -> {
            LOGGER.info(omdbMovie.toString());
        });

        return movies;
    }

    private JsonNode get(URL url) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readTree(url);
    }

    private OmdbMovie setMovie(JsonNode jsonNode) {
        OmdbMovie movie = new OmdbMovie();
        movie.setTitle(jsonNode.get("Title").asText());
        movie.setPoster(jsonNode.get("Poster").asText());
        movie.setYear(jsonNode.get("Year").asText());
        movie.setType(jsonNode.get("Type").asText());
        movie.setImdbID(jsonNode.get("imdbID").asText());
        return movie;
    }
}
