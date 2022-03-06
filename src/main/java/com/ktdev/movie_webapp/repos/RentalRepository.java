package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.Rental;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RentalRepository extends JpaRepository<Rental, Long> {
}
