package com.ktdev.movie_webapp.rest;

import com.ktdev.movie_webapp.model.MovieDTO;
import com.ktdev.movie_webapp.service.MovieService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/movies", produces = MediaType.APPLICATION_JSON_VALUE)
public class MovieController {

    private final MovieService movieService;

    public MovieController(final MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        return ResponseEntity.ok(movieService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getMovie(@PathVariable final Long id) {
        return ResponseEntity.ok(movieService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createMovie(@RequestBody @Valid final MovieDTO movieDTO) {
        return new ResponseEntity<>(movieService.create(movieDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable final Long id,
            @RequestBody @Valid final MovieDTO movieDTO) {
        movieService.update(id, movieDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable final Long id) {
        movieService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
