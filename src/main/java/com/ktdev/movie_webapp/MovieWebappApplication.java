package com.ktdev.movie_webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication
public class MovieWebappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MovieWebappApplication.class, args);
    }

}
