package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.Genre;
import org.springframework.data.jpa.repository.JpaRepository;


public interface GenreRepository extends JpaRepository<Genre, Long> {
}
