package mobify.app.security.domain.contracts;

import mobify.app.security.domain.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;

public interface PublicContract {
    interface PublicController {

        String homePath();

        String aboutPath();

        String redirect();

        String loginPath();

        String registrationPath();
    }

    interface PublicUseCase {
        String home();

        void setController(PublicController publicController);

        String about();

        String login(Authentication auth);

        ModelAndView registration();

        ModelAndView createNewUser(User user, BindingResult bindingResult);
    }
}
