package com.avad.ebookie.domain.home;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("home")
    public String home(HttpServletRequest request) {
        return "content/home";
    }
}
