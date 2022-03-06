package com.ktdev.movie_webapp.rest;

import com.ktdev.movie_webapp.model.RentalDTO;
import com.ktdev.movie_webapp.service.RentalService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/rentals", produces = MediaType.APPLICATION_JSON_VALUE)
public class RentalController {

    private final RentalService rentalService;

    public RentalController(final RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @GetMapping
    public ResponseEntity<List<RentalDTO>> getAllRentals() {
        return ResponseEntity.ok(rentalService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRental(@PathVariable final Long id) {
        return ResponseEntity.ok(rentalService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createRental(@RequestBody @Valid final RentalDTO rentalDTO) {
        return new ResponseEntity<>(rentalService.create(rentalDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateRental(@PathVariable final Long id,
            @RequestBody @Valid final RentalDTO rentalDTO) {
        rentalService.update(id, rentalDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRental(@PathVariable final Long id) {
        rentalService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
