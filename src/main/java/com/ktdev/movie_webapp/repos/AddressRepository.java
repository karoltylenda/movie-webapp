package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AddressRepository extends JpaRepository<Address, Long> {
}
