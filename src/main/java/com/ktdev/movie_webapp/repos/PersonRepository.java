package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PersonRepository extends JpaRepository<Person, Long> {
}
