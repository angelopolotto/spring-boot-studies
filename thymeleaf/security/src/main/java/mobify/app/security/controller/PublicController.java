package mobify.app.security.controller;

import mobify.app.security.entity.User;
import mobify.app.security.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PublicController {
    private final UserService userService;

    public PublicController(UserService userService) {
        this.userService = userService;
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
        User userExists = userService.findUserByEmail(user.getEmail());
        ModelAndView modelAndView = new ModelAndView();

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("/public/registration");
        } else {
            PasswordEncoder encoder = new BCryptPasswordEncoder();
            String encodedPass = encoder.encode(user.getPassword());

            user.setPassword(encodedPass);
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("/public/registration");

        }
        return modelAndView;
    }
}
