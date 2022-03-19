package com.ktdev.movie_webapp.controller;


import com.ktdev.movie_webapp.service.UserService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;
import java.util.Optional;

@RequestMapping("/genres")
public class GenresController {

    private final UserService userService;

    public GenresController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String genres(
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page,
            Model model, Principal principal){


        model.addAttribute("loggedUser", userService.getByUsername(principal.getName()));
        return "genres";
    }
}
