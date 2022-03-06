package com.ktdev.movie_webapp.service;

import com.ktdev.movie_webapp.domain.Genre;
import com.ktdev.movie_webapp.domain.Movie;
import com.ktdev.movie_webapp.domain.Person;
import com.ktdev.movie_webapp.model.MovieDTO;
import com.ktdev.movie_webapp.repos.GenreRepository;
import com.ktdev.movie_webapp.repos.MovieRepository;
import com.ktdev.movie_webapp.repos.PersonRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Transactional
@Service
public class MovieService {

    private final MovieRepository movieRepository;
    private final GenreRepository genreRepository;
    private final PersonRepository personRepository;

    public MovieService(final MovieRepository movieRepository,
            final GenreRepository genreRepository, final PersonRepository personRepository) {
        this.movieRepository = movieRepository;
        this.genreRepository = genreRepository;
        this.personRepository = personRepository;
    }

    public List<MovieDTO> findAll() {
        return movieRepository.findAll()
                .stream()
                .map(movie -> mapToDTO(movie, new MovieDTO()))
                .collect(Collectors.toList());
    }

    public MovieDTO get(final Long id) {
        return movieRepository.findById(id)
                .map(movie -> mapToDTO(movie, new MovieDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final MovieDTO movieDTO) {
        final Movie movie = new Movie();
        mapToEntity(movieDTO, movie);
        return movieRepository.save(movie).getId();
    }

    public void update(final Long id, final MovieDTO movieDTO) {
        final Movie movie = movieRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(movieDTO, movie);
        movieRepository.save(movie);
    }

    public void delete(final Long id) {
        movieRepository.deleteById(id);
    }

    private MovieDTO mapToDTO(final Movie movie, final MovieDTO movieDTO) {
        movieDTO.setId(movie.getId());
        movieDTO.setTitle(movie.getTitle());
        movieDTO.setYear(movie.getYear());
        movieDTO.setReleased(movie.getReleased());
        movieDTO.setRuntime(movie.getRuntime());
        movieDTO.setPlot(movie.getPlot());
        movieDTO.setLanguage(movie.getLanguage());
        movieDTO.setCountry(movie.getCountry());
        movieDTO.setAwards(movie.getAwards());
        movieDTO.setPoster(movie.getPoster());
        movieDTO.setImdbRating(movie.getImdbRating());
        movieDTO.setImdbVotes(movie.getImdbVotes());
        movieDTO.setImdbId(movie.getImdbId());
        movieDTO.setType(movie.getType());
        movieDTO.setDvd(movie.getDvd());
        movieDTO.setBoxOffice(movie.getBoxOffice());
        movieDTO.setWebsite(movie.getWebsite());
        movieDTO.setPrice(movie.getPrice());
        movieDTO.setGenreTypes(movie.getGenreTypeGenres() == null ? null : movie.getGenreTypeGenres().stream()
                .map(genre -> genre.getId())
                .collect(Collectors.toList()));
        movieDTO.setDirectors(movie.getDirectorPersons() == null ? null : movie.getDirectorPersons().stream()
                .map(person -> person.getId())
                .collect(Collectors.toList()));
        movieDTO.setWriters(movie.getWriterPersons() == null ? null : movie.getWriterPersons().stream()
                .map(person -> person.getId())
                .collect(Collectors.toList()));
        movieDTO.setActors(movie.getActorPersons() == null ? null : movie.getActorPersons().stream()
                .map(person -> person.getId())
                .collect(Collectors.toList()));
        return movieDTO;
    }

    private Movie mapToEntity(final MovieDTO movieDTO, final Movie movie) {
        movie.setTitle(movieDTO.getTitle());
        movie.setYear(movieDTO.getYear());
        movie.setReleased(movieDTO.getReleased());
        movie.setRuntime(movieDTO.getRuntime());
        movie.setPlot(movieDTO.getPlot());
        movie.setLanguage(movieDTO.getLanguage());
        movie.setCountry(movieDTO.getCountry());
        movie.setAwards(movieDTO.getAwards());
        movie.setPoster(movieDTO.getPoster());
        movie.setImdbRating(movieDTO.getImdbRating());
        movie.setImdbVotes(movieDTO.getImdbVotes());
        movie.setImdbId(movieDTO.getImdbId());
        movie.setType(movieDTO.getType());
        movie.setDvd(movieDTO.getDvd());
        movie.setBoxOffice(movieDTO.getBoxOffice());
        movie.setWebsite(movieDTO.getWebsite());
        movie.setPrice(movieDTO.getPrice());
        if (movieDTO.getGenreTypes() != null) {
            final List<Genre> genreTypes = genreRepository.findAllById(movieDTO.getGenreTypes());
            if (genreTypes.size() != movieDTO.getGenreTypes().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of genreTypes not found");
            }
            movie.setGenreTypeGenres(genreTypes.stream().collect(Collectors.toSet()));
        }
        if (movieDTO.getDirectors() != null) {
            final List<Person> directors = personRepository.findAllById(movieDTO.getDirectors());
            if (directors.size() != movieDTO.getDirectors().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of directors not found");
            }
            movie.setDirectorPersons(directors.stream().collect(Collectors.toSet()));
        }
        if (movieDTO.getWriters() != null) {
            final List<Person> writers = personRepository.findAllById(movieDTO.getWriters());
            if (writers.size() != movieDTO.getWriters().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of writers not found");
            }
            movie.setWriterPersons(writers.stream().collect(Collectors.toSet()));
        }
        if (movieDTO.getActors() != null) {
            final List<Person> actors = personRepository.findAllById(movieDTO.getActors());
            if (actors.size() != movieDTO.getActors().size()) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "one of actors not found");
            }
            movie.setActorPersons(actors.stream().collect(Collectors.toSet()));
        }
        return movie;
    }

}
