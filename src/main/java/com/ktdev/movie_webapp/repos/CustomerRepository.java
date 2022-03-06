package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
