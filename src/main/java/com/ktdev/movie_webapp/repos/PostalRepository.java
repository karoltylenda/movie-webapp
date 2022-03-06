package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.Postal;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostalRepository extends JpaRepository<Postal, Long> {
}
