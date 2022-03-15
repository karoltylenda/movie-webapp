package com.ktdev.movie_webapp.controller;

import com.ktdev.movie_webapp.model.UserDTO;
import com.ktdev.movie_webapp.model.UserRole;
import com.ktdev.movie_webapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        UserDTO user = userService.getByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("role", UserRole.values());
        return "profile";
    }
}
