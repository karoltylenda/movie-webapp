package com.ktdev.movie_webapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;


public class PaymentDTO {

    private Long id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    @Digits(integer = 2, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "96.08")
    private BigDecimal amount;

    private Long rental;

    private Long customer;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(final LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(final BigDecimal amount) {
        this.amount = amount;
    }

    public Long getRental() {
        return rental;
    }

    public void setRental(final Long rental) {
        this.rental = rental;
    }

    public Long getCustomer() {
        return customer;
    }

    public void setCustomer(final Long customer) {
        this.customer = customer;
    }

}
