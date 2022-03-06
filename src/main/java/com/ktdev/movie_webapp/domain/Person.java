package com.ktdev.movie_webapp.domain;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;


@Entity
public class Person {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @ManyToMany(mappedBy = "directorPersons")
    private Set<Movie> directorMovies;

    @ManyToMany(mappedBy = "writerPersons")
    private Set<Movie> writerMovies;

    @ManyToMany(mappedBy = "actorPersons")
    private Set<Movie> actorMovies;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Set<Movie> getDirectorMovies() {
        return directorMovies;
    }

    public void setDirectorMovies(final Set<Movie> directorMovies) {
        this.directorMovies = directorMovies;
    }

    public Set<Movie> getWriterMovies() {
        return writerMovies;
    }

    public void setWriterMovies(final Set<Movie> writerMovies) {
        this.writerMovies = writerMovies;
    }

    public Set<Movie> getActorMovies() {
        return actorMovies;
    }

    public void setActorMovies(final Set<Movie> actorMovies) {
        this.actorMovies = actorMovies;
    }

}
