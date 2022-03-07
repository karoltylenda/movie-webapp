package com.ktdev.movie_webapp.controller;

import com.ktdev.movie_webapp.model.UserDTO;
import com.ktdev.movie_webapp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String users(Model model){
        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @PostMapping("/addUser")
    public RedirectView addUser(UserDTO userDTO){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDTO.setPassword(bCryptPasswordEncoder.encode(userDTO.getPassword()));
        userService.create(userDTO);
        return new RedirectView("/users");
    }
}
