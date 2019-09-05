package mobify.app.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PublicController {
    @GetMapping("/")
    public String home() {
        return "/public/home";
    }

    @GetMapping("/about")
    public String about() {
        return "/public/about";
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth.getPrincipal() instanceof UserDetails) {
            return "redirect:/";
        } else return "/public/login";
    }

    @PostMapping("/registration")
    public String registration() {
        return "/public/registration";
    }
}
