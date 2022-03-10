package com.ktdev.movie_webapp.domain;

import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


@Entity
public class Movie {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column
    private Integer year;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate released;

    @Column
    private String runtime;

    @Column(columnDefinition = "longtext")
    private String plot;

    @Column
    private String language;

    @Column
    private String country;

    @Column
    private String awards;

    @Column(columnDefinition = "longtext")
    private String poster;

    @Column
    private String imdbRating;

    @Column
    private String imdbVotes;

    @Column
    private String imdbId;

    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private String type;

    @Column
    private LocalDate dvd;

    @Column
    private String boxOffice;

    @Column
    private String website;

    @Column(precision = 2, scale = 2)
    private BigDecimal price;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "genre_type",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private Set<Genre> genreTypeGenres;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "director",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> directorPersons;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "writer",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> writerPersons;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
            name = "actor",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "person_id")
    )
    private Set<Person> actorPersons;

    @OneToMany(mappedBy = "movie")
    private Set<Rental> movieRentals;

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

    public Set<Genre> getGenreTypeGenres() {
        return genreTypeGenres;
    }

    public void setGenreTypeGenres(final Set<Genre> genreTypeGenres) {
        this.genreTypeGenres = genreTypeGenres;
    }

    public Set<Person> getDirectorPersons() {
        return directorPersons;
    }

    public void setDirectorPersons(final Set<Person> directorPersons) {
        this.directorPersons = directorPersons;
    }

    public Set<Person> getWriterPersons() {
        return writerPersons;
    }

    public void setWriterPersons(final Set<Person> writerPersons) {
        this.writerPersons = writerPersons;
    }

    public Set<Person> getActorPersons() {
        return actorPersons;
    }

    public void setActorPersons(final Set<Person> actorPersons) {
        this.actorPersons = actorPersons;
    }

    public Set<Rental> getMovieRentals() {
        return movieRentals;
    }

    public void setMovieRentals(final Set<Rental> movieRentals) {
        this.movieRentals = movieRentals;
    }

}
