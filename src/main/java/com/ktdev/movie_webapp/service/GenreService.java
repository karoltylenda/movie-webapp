package com.ktdev.movie_webapp.service;

import com.ktdev.movie_webapp.domain.Genre;
import com.ktdev.movie_webapp.model.GenreDTO;
import com.ktdev.movie_webapp.repos.GenreRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class GenreService {

    private final GenreRepository genreRepository;
    private static final Logger LOGGER = Logger.getLogger(GenreService.class.getName());

    public GenreService(final GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<GenreDTO> findAll() {
        return genreRepository.findAll()
                .stream()
                .map(genre -> mapToDTO(genre, new GenreDTO()))
                .collect(Collectors.toList());
    }

    public Page<GenreDTO> findPaginated(Pageable pageable){
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;
        List<GenreDTO> list;

        if (findAll().size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, findAll().size());
            list = findAll().subList(startItem, toIndex);
        }

        Page<GenreDTO> genreDTOPage = new PageImpl<>(list, PageRequest.of(currentPage, pageSize), findAll().size());

        return genreDTOPage;
    }

    public GenreDTO get(final Long id) {
        return genreRepository.findById(id)
                .map(genre -> mapToDTO(genre, new GenreDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final GenreDTO genreDTO) {
        final Genre genre = new Genre();
        mapToEntity(genreDTO, genre);
        return genreRepository.save(genre).getId();
    }

    public void update(final Long id, final GenreDTO genreDTO) {
        final Genre genre = genreRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(genreDTO, genre);
        genreRepository.save(genre);
    }

    public void delete(final Long id) {
        genreRepository.deleteById(id);
    }

    public GenreDTO getByName(String name){
        return genreRepository.getByName(name)
                .map(genre -> mapToDTO(genre, new GenreDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Map<String, String> idGenreMap(){
        Map<String, String> map = new HashMap<>();
        for (GenreDTO genre: findAll()) {
            map.put(genre.getId().toString(), genre.getName());
        }
        return map;
    }

    private GenreDTO mapToDTO(final Genre genre, final GenreDTO genreDTO) {
        genreDTO.setId(genre.getId());
        genreDTO.setName(genre.getName());
        return genreDTO;
    }

    private Genre mapToEntity(final GenreDTO genreDTO, final Genre genre) {
        genre.setName(genreDTO.getName());
        return genre;
    }

}
