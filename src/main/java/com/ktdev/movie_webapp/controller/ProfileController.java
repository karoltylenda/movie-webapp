package com.ktdev.movie_webapp.controller;

import com.ktdev.movie_webapp.model.UserDTO;
import com.ktdev.movie_webapp.model.UserRole;
import com.ktdev.movie_webapp.service.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.logging.Logger;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;
    private static final Logger LOGGER = Logger.getLogger(ProfileController.class.getName());

    public ProfileController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String profile(Model model, Principal principal){
        UserDTO user = userService.getByUsername(principal.getName());
        model.addAttribute("user", user);
        model.addAttribute("role", UserRole.values());
        return "profile";
    }

    @PostMapping("/update")
    public RedirectView updateProfile(UserDTO userDTO) {
        userService.update(userDTO.getId(), setUser(userDTO));
        return new RedirectView("/profile");
    }

    @PostMapping("/changePassword")
    public RedirectView changePassword(Long id, String currentPassword, String password, String passwordConfirmation){
        updatePassword(id, currentPassword, password, passwordConfirmation);
        return new RedirectView("/profile");
    }

    private UserDTO setUser(UserDTO userDTO){
        UserDTO userToUpdate = userService.get(userDTO.getId());
        userToUpdate.setFirstName(userDTO.getFirstName());
        userToUpdate.setLastName(userDTO.getLastName());
        userToUpdate.setOmdbApiKey(userDTO.getOmdbApiKey());
        userToUpdate.setRole(userDTO.getRole());
        return userToUpdate;
    }

    private void updatePassword(Long id, String currentPassword, String password, String passwordConfirmation){
        UserDTO userDTO = userService.get(id);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        if (isPasswordMatches(password, passwordConfirmation) && isUserPasswordMatch(userDTO, currentPassword)){
            userDTO.setPassword(encoder.encode(passwordConfirmation));
            userService.update(userDTO.getId(), userDTO);
        }
    }

    private Boolean isPasswordMatches(String password, String passwordConfirmation){
        if (password.equals(passwordConfirmation)){
            return true;
        } else return false;
    }

    private Boolean isUserPasswordMatch(UserDTO userDTO, String currentPassword){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.matches(currentPassword, userDTO.getPassword());
    }
}
