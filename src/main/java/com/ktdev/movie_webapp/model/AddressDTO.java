package com.ktdev.movie_webapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class AddressDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String street;

    @Size(max = 255)
    private String street2;

    @Size(max = 255)
    private String info;

    private Long postal;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(final String street) {
        this.street = street;
    }

    public String getStreet2() {
        return street2;
    }

    public void setStreet2(final String street2) {
        this.street2 = street2;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(final String info) {
        this.info = info;
    }

    public Long getPostal() {
        return postal;
    }

    public void setPostal(final Long postal) {
        this.postal = postal;
    }

}
