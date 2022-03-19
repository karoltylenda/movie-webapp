package com.ktdev.movie_webapp;

import com.ktdev.movie_webapp.model.UserDTO;
import com.ktdev.movie_webapp.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.logging.Logger;

@Controller
public class HomeController {

    private final UserService userService;
    private static final Logger LOGGER = Logger.getLogger(HomeController.class.getName());

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public RedirectView main() {
        return new RedirectView("/index");
    }

    @GetMapping("/index")
    public String index(Model model, Principal principal) {
        UserDTO userDTO = userService.getByUsername(principal.getName());
        model.addAttribute("loggedUser", userDTO);
        return "index";
    }

}
