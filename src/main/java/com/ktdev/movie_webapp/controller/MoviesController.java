package com.ktdev.movie_webapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.logging.Logger;

@Controller
public class MoviesController {

    private final Logger LOGGER = Logger.getLogger(MoviesController.class.getName());

    @GetMapping("/movies")
    public String movies(){

        return "movies";
    }

    @GetMapping("/movies/omdbapi/search")
    public String movieFromOmdb(){
//        String str = "http://omdbapi.com/?"+"apikey="+apikey+"&t="+t+"&plot="+plot;
//        URL url = new URL(str);
//        HttpURLConnection http = (HttpURLConnection)url.openConnection();
//        http.setRequestProperty("Accept", "application/json");
//
//        LOGGER.log(Level.INFO, http.getResponseCode() + " " + http.getResponseMessage());
//        LOGGER.info(http.getContent());
//        http.disconnect();
        return "movies";
    }
}
