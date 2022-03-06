package com.ktdev.movie_webapp.repos;

import com.ktdev.movie_webapp.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
