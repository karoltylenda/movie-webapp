package com.ktdev.movie_webapp.service;

import com.ktdev.movie_webapp.domain.Customer;
import com.ktdev.movie_webapp.domain.Movie;
import com.ktdev.movie_webapp.domain.Rental;
import com.ktdev.movie_webapp.model.RentalDTO;
import com.ktdev.movie_webapp.repos.CustomerRepository;
import com.ktdev.movie_webapp.repos.MovieRepository;
import com.ktdev.movie_webapp.repos.RentalRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class RentalService {

    private final RentalRepository rentalRepository;
    private final MovieRepository movieRepository;
    private final CustomerRepository customerRepository;

    public RentalService(final RentalRepository rentalRepository,
            final MovieRepository movieRepository, final CustomerRepository customerRepository) {
        this.rentalRepository = rentalRepository;
        this.movieRepository = movieRepository;
        this.customerRepository = customerRepository;
    }

    public List<RentalDTO> findAll() {
        return rentalRepository.findAll()
                .stream()
                .map(rental -> mapToDTO(rental, new RentalDTO()))
                .collect(Collectors.toList());
    }

    public RentalDTO get(final Long id) {
        return rentalRepository.findById(id)
                .map(rental -> mapToDTO(rental, new RentalDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final RentalDTO rentalDTO) {
        final Rental rental = new Rental();
        mapToEntity(rentalDTO, rental);
        return rentalRepository.save(rental).getId();
    }

    public void update(final Long id, final RentalDTO rentalDTO) {
        final Rental rental = rentalRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(rentalDTO, rental);
        rentalRepository.save(rental);
    }

    public void delete(final Long id) {
        rentalRepository.deleteById(id);
    }

    private RentalDTO mapToDTO(final Rental rental, final RentalDTO rentalDTO) {
        rentalDTO.setId(rental.getId());
        rentalDTO.setRentalDate(rental.getRentalDate());
        rentalDTO.setReturnDate(rental.getReturnDate());
        rentalDTO.setIsPaid(rental.getIsPaid());
        rentalDTO.setMovie(rental.getMovie() == null ? null : rental.getMovie().getId());
        rentalDTO.setCustomer(rental.getCustomer() == null ? null : rental.getCustomer().getId());
        return rentalDTO;
    }

    private Rental mapToEntity(final RentalDTO rentalDTO, final Rental rental) {
        rental.setRentalDate(rentalDTO.getRentalDate());
        rental.setReturnDate(rentalDTO.getReturnDate());
        rental.setIsPaid(rentalDTO.getIsPaid());
        if (rentalDTO.getMovie() != null && (rental.getMovie() == null || !rental.getMovie().getId().equals(rentalDTO.getMovie()))) {
            final Movie movie = movieRepository.findById(rentalDTO.getMovie())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "movie not found"));
            rental.setMovie(movie);
        }
        if (rentalDTO.getCustomer() != null && (rental.getCustomer() == null || !rental.getCustomer().getId().equals(rentalDTO.getCustomer()))) {
            final Customer customer = customerRepository.findById(rentalDTO.getCustomer())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "customer not found"));
            rental.setCustomer(customer);
        }
        return rental;
    }

}
