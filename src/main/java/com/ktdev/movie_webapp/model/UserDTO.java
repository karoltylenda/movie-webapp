package com.ktdev.movie_webapp.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


public class UserDTO {

    private Long id;

    @Size(max = 255)
    private String firstName;

    @Size(max = 255)
    private String lastName;

    @NotNull
    @Size(max = 255)
    private String username;

    @NotNull
    @Size(max = 255)
    private String password;

    @Size(max = 255)
    private String omdbApiKey;

    @NotNull
    private UserRole role;

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

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getOmdbApiKey() {
        return omdbApiKey;
    }

    public void setOmdbApiKey(final String omdbApiKey) {
        this.omdbApiKey = omdbApiKey;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(final UserRole role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", password='" + "HIDDEN" + '\'' +
                ", omdbApiKey='" + omdbApiKey + '\'' +
                ", role=" + role +
                '}';
    }
}
