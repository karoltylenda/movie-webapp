package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> getByName(String name);
}
