package mobify.app.security.infrastructure.controller;

import mobify.app.security.domain.entity.User;
import mobify.app.security.domain.interfaces.PublicUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PublicController {
    private final PublicUseCase publicUseCase;

    @Autowired
    public PublicController(PublicUseCase publicUseCase) {
        this.publicUseCase = publicUseCase;
    }

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

    @GetMapping("/registration")
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("/public/registration");
        return modelAndView;
    }

    @PostMapping("/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        User userExists = publicUseCase.findUserByEmail(user.getEmail());
        ModelAndView modelAndView = new ModelAndView();

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/public/registration");
        } else {
            publicUseCase.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/public/registration");

        }
        return modelAndView;
    }
}
