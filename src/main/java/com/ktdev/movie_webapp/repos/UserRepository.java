package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.User;
import com.ktdev.movie_webapp.model.UserDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> getUserByUsername(String username);
}
