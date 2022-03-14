package com.ktdev.movie_webapp.controller;

import com.ktdev.movie_webapp.model.GenreDTO;
import com.ktdev.movie_webapp.model.MovieDTO;
import com.ktdev.movie_webapp.model.UserDTO;
import com.ktdev.movie_webapp.service.GenreService;
import com.ktdev.movie_webapp.service.MovieService;
import com.ktdev.movie_webapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/movies")
public class MoviesController {

    private final MovieService movieService;
    private final GenreService genreService;
    private final UserService userService;

    private final Logger LOGGER = Logger.getLogger(MoviesController.class.getName());

    public MoviesController(MovieService movieService, GenreService genreService, UserService userService) {
        this.movieService = movieService;
        this.genreService = genreService;
        this.userService = userService;
    }

    @GetMapping
    public String movies(Model model){
        model.addAttribute("movies", movieService.findAll());
        return "movies";
    }

    @PostMapping("/addMovie")
    public RedirectView addMovie(MovieDTO movieDTO, String genres){
        String[] strings = genres.split(",");
        List<Long> genreDTOS = new ArrayList<>();
        for (String string : strings) {
            GenreDTO genreDTO = new GenreDTO();
            try {
                genreDTO = genreService.getByName(string);
            } catch (ResponseStatusException exception) {
                genreDTO.setName(string);
                genreDTO = genreService.get(genreService.create(genreDTO));
            }
            genreDTOS.add(genreDTO.getId());
        }
        movieDTO.setGenreTypes(genreDTOS);
        movieService.create(movieDTO);
        return new RedirectView("/movies");
    }

    @GetMapping("/searchInOmdb")
    public String searchInOmdb(Model model, Principal principal){
        String username = principal.getName();
        UserDTO userDTO = userService.getByUsername(username);
        LOGGER.info(userDTO.toString());
        model.addAttribute("apikey", userDTO.getOmdbApiKey());
        return "searchMovie";
    }

    @GetMapping("/addNewMovie")
    public String addNewMovie(){
        return "addNewMovie";
    }
}
