package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long> {
}
