package com.ktdev.movie_webapp.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class MovieDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String title;

    private Integer year;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate released;

    @Size(max = 255)
    private String runtime;

    private String plot;

    @Size(max = 255)
    private String language;

    @Size(max = 255)
    private String country;

    @Size(max = 255)
    private String awards;

    private String poster;

    @Size(max = 255)
    private String imdbRating;

    @Size(max = 255)
    private String imdbVotes;

    @Size(max = 255)
    private String imdbId;

    @Size(max = 255)
    private String type;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dvd;

    @Size(max = 255)
    private String boxOffice;

    @Size(max = 255)
    private String website;

    @Digits(integer = 2, fraction = 2)
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(type = "string", example = "71.08")
    private BigDecimal price;

    private List<Long> genreTypes;

    private List<Long> directors;

    private List<Long> writers;

    private List<Long> actors;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(final Integer year) {
        this.year = year;
    }

    public LocalDate getReleased() {
        return released;
    }

    public void setReleased(final LocalDate released) {
        this.released = released;
    }

    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(final String runtime) {
        this.runtime = runtime;
    }

    public String getPlot() {
        return plot;
    }

    public void setPlot(final String plot) {
        this.plot = plot;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(final String language) {
        this.language = language;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public String getAwards() {
        return awards;
    }

    public void setAwards(final String awards) {
        this.awards = awards;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(final String poster) {
        this.poster = poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(final String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(final String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(final String imdbId) {
        this.imdbId = imdbId;
    }

    public String getType() {
        return type;
    }

    public void setType(final String type) {
        this.type = type;
    }

    public LocalDate getDvd() {
        return dvd;
    }

    public void setDvd(final LocalDate dvd) {
        this.dvd = dvd;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public void setBoxOffice(final String boxOffice) {
        this.boxOffice = boxOffice;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(final String website) {
        this.website = website;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(final BigDecimal price) {
        this.price = price;
    }

    public List<Long> getGenreTypes() {
        return genreTypes;
    }

    public void setGenreTypes(final List<Long> genreTypes) {
        this.genreTypes = genreTypes;
    }

    public List<Long> getDirectors() {
        return directors;
    }

    public void setDirectors(final List<Long> directors) {
        this.directors = directors;
    }

    public List<Long> getWriters() {
        return writers;
    }

    public void setWriters(final List<Long> writers) {
        this.writers = writers;
    }

    public List<Long> getActors() {
        return actors;
    }

    public void setActors(final List<Long> actors) {
        this.actors = actors;
    }

}
