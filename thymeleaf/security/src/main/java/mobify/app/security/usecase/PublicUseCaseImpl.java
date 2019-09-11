package mobify.app.security.usecase;

import mobify.app.security.domain.contracts.PublicContract;
import mobify.app.security.domain.entity.Role;
import mobify.app.security.domain.entity.RoleConfig;
import mobify.app.security.domain.entity.User;
import mobify.app.security.domain.repository.RoleRepository;
import mobify.app.security.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.HashSet;

@Service
public class PublicUseCaseImpl implements PublicContract.PublicUseCase {
    private PublicContract.PublicController publicController;
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public PublicUseCaseImpl(UserRepository userRepository,
                             RoleRepository roleRepository,
                             PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void setController(PublicContract.PublicController publicController) {
        this.publicController = publicController;
    }

    @Override
    public String about() {
        return publicController.aboutPath();
    }

    @Override
    public String login(Authentication auth) {
        if (auth.getPrincipal() instanceof UserDetails) {
            return publicController.redirect();
        } else {
            return publicController.loginPath();
        }
    }

    @Override
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName(publicController.registrationPath());
        return modelAndView;
    }

    @Override
    public ModelAndView createNewUser(User user, BindingResult bindingResult) {
        User userExists = findUserByEmail(user.getEmail());
        ModelAndView modelAndView = new ModelAndView();

        if (userExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "There is already a user registered with the email provided");
        }

        if (bindingResult.hasErrors()) {
            modelAndView.setViewName(publicController.registrationPath());
        } else {
            saveUser(user);
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName(publicController.registrationPath());
        }

        return modelAndView;
    }

    private User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private void saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role userRole = roleRepository.findByRole(RoleConfig.ADMIN.role);
        user.setRoles(new HashSet<>(Collections.singletonList(userRole)));
        userRepository.save(user);
    }

    @Override
    public String home() {
        return publicController.homePath();
    }
}
