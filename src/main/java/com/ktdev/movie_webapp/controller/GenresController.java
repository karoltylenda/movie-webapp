package com.ktdev.movie_webapp.controller;


import com.ktdev.movie_webapp.model.GenreDTO;
import com.ktdev.movie_webapp.model.MovieDTO;
import com.ktdev.movie_webapp.service.GenreService;
import com.ktdev.movie_webapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/genres")
public class GenresController {

    private final UserService userService;
    private final GenreService genreService;
    private static final Logger LOGGER = Logger.getLogger(GenresController.class.getName());

    public GenresController(UserService userService, GenreService genreService) {
        this.userService = userService;
        this.genreService = genreService;
    }

    @GetMapping
    public String genres(
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page,
            @RequestParam("name") Optional<String> name,
            Model model, Principal principal){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<GenreDTO> genresPage = genreService.findPaginated(PageRequest.of(currentPage -1, pageSize), genreService.getListOfGenres(name));
        List<Integer> pageNumbers = Collections.emptyList();

        int totalPages = genresPage.getTotalPages();
        if (totalPages > 0) {
            pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }

        model.addAttribute("pageNumbers", pageNumbers);
        model.addAttribute("genres", genresPage);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("loggedUser", userService.getByUsername(principal.getName()));
        return "genres";
    }

    @GetMapping("/delete/{id}")
    public RedirectView delete(@PathVariable Long id){
        genreService.delete(id);
        return new RedirectView("/genres");
    }

    @GetMapping("/addGenre")
    public RedirectView add(GenreDTO genreDTO, RedirectAttributes redirectAttributes){
        try {
            genreService.create(genreDTO);
            redirectAttributes.addFlashAttribute("succcess", "Database updated.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Something went wrong.");
        }
        return new RedirectView("/genres");
    }

    @PostMapping("/edit/{id}/update")
    public RedirectView update(@PathVariable Long id, GenreDTO genreDTO, RedirectAttributes redirectAttributes) {
        try {
            genreService.update(id, genreDTO);
            redirectAttributes.addFlashAttribute("succcess", "Database updated.");
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
            redirectAttributes.addFlashAttribute("error", "Something went wrong.");
        }
        return new RedirectView("/genres");
    }
}
