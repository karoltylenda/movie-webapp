package com.ktdev.movie_webapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class PostalDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String code;

    private Long city;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public Long getCity() {
        return city;
    }

    public void setCity(final Long city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "PostalDTO{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", city=" + city +
                '}';
    }
}
