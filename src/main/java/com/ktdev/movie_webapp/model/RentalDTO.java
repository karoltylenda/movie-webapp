package com.ktdev.movie_webapp.model;

import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;


public class RentalDTO {

    private Long id;

    @NotNull
    private LocalDateTime rentalDate;

    private LocalDateTime returnDate;

    private Boolean isPaid;

    private Long movie;

    private Long customer;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(final LocalDateTime rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(final LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(final Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public Long getMovie() {
        return movie;
    }

    public void setMovie(final Long movie) {
        this.movie = movie;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(final Long customer) {
        this.customer = customer;
    }

}
