package com.ktdev.movie_webapp;

import com.ktdev.movie_webapp.model.UserDTO;
import com.ktdev.movie_webapp.model.UserRole;
import com.ktdev.movie_webapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;


@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal){
        UserDTO user = userService.getByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("role", UserRole.values());
        return "profile";
    }
}
