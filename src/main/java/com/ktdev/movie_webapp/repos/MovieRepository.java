package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.Movie;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MovieRepository extends JpaRepository<Movie, Long> {
}
