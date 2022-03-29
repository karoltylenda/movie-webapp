package com.ktdev.movie_webapp.controller;


import com.ktdev.movie_webapp.model.GenreDTO;
import com.ktdev.movie_webapp.model.MovieDTO;
import com.ktdev.movie_webapp.service.GenreService;
import com.ktdev.movie_webapp.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/genres")
public class GenresController {

    private final UserService userService;
    private final GenreService genreService;

    public GenresController(UserService userService, GenreService genreService) {
        this.userService = userService;
        this.genreService = genreService;
    }

    @GetMapping
    public String genres(
            @RequestParam("size") Optional<Integer> size,
            @RequestParam("page") Optional<Integer> page,
            Model model, Principal principal){

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);
        Page<GenreDTO> genresPage = genreService.findPaginated(PageRequest.of(currentPage -1, pageSize));

        int totalPages = genresPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

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
}
