package com.ktdev.movie_webapp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {

    @GetMapping("/")
    public String main() {
        return "index";
    }

    @GetMapping("/index")
    public String index() {
        return "index";
    }

}
