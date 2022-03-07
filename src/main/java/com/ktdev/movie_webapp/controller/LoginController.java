package com.ktdev.movie_webapp.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.util.logging.Logger;

@Controller
public class LoginController {

    private static final Logger LOGGER = Logger.getLogger(LoginController.class.getName());

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/logout")
    private String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "login";
    }
}
