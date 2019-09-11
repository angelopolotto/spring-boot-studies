package mobify.app.security.infrastructure.controller;

import mobify.app.security.domain.contracts.PublicContract;
import mobify.app.security.domain.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
public class PublicControllerImpl implements PublicContract.PublicController {
    private final PublicContract.PublicUseCase publicUseCase;

    @Autowired
    public PublicControllerImpl(PublicContract.PublicUseCase publicUseCase) {
        this.publicUseCase = publicUseCase;
        publicUseCase.setController(this);
    }

    @GetMapping("/")
    public String home() {
        return publicUseCase.home();
    }

    @GetMapping("/about")
    public String about() {
        return publicUseCase.about();
    }

    @GetMapping("/login")
    public String login() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return publicUseCase.login(auth);
    }

    @GetMapping("/registration")
    public ModelAndView registration() {
        return publicUseCase.registration();
    }

    @PostMapping("/registration")
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        return publicUseCase.createNewUser(user, bindingResult);
    }

    @Override
    public String homePath() {
        return "/public/home";
    }

    @Override
    public String aboutPath() {
        return "/public/about";
    }

    @Override
    public String redirect() {
        return "redirect:/";
    }

    @Override
    public String loginPath() {
        return "/public/login";
    }

    @Override
    public String registrationPath() {
        return "/public/registration";
    }
}
