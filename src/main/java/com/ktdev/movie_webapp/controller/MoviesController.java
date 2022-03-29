package com.ktdev.movie_webapp.controller;

import com.ktdev.movie_webapp.model.GenreDTO;
import com.ktdev.movie_webapp.model.MovieDTO;
import com.ktdev.movie_webapp.model.UserDTO;
import com.ktdev.movie_webapp.service.GenreService;
import com.ktdev.movie_webapp.service.MovieService;
import com.ktdev.movie_webapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
    public String movies(
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("size") Optional<Integer> size,
            Model model,
            Principal principal){
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<MovieDTO> moviesPage = movieService.findPaginated(PageRequest.of(currentPage -1, pageSize));

        int totalPages = moviesPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        UserDTO userDTO = userService.getByUsername(principal.getName());
        model.addAttribute("loggedUser", userDTO);
        model.addAttribute("movies", moviesPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("genresMap", genreService.idGenreMap());
        return "movies";
    }

    @GetMapping("/delete/{id}")
    public RedirectView deleteMovie(@PathVariable Long id){
        movieService.delete(id);
        return new RedirectView("/movies");
    }

    @GetMapping("/edit/{id}")
    public String editMovie(@PathVariable Long id, Model model, Principal principal){
        UserDTO loggedUser = userService.getByUsername(principal.getName());
        MovieDTO movieDTO = movieService.get(id);
        model.addAttribute("loggedUser", loggedUser);
        model.addAttribute("movie", movieDTO);
        model.addAttribute("genres", genreService.findAll());
        return "editMovie";
    }

    @PostMapping("/edit/{id}/update")
    public RedirectView updateMovie(@PathVariable Long id, MovieDTO movieDTO){
        movieService.update(id, movieDTO);
        return new RedirectView("/movies/edit/"+id);
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
        model.addAttribute("apikey", userDTO.getOmdbApiKey());
        return "searchMovie";
    }

    @GetMapping("/old")
    public String oldMovies(){
        return "moviesOld";
    }

}
