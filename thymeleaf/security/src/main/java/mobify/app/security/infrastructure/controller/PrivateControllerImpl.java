package mobify.app.security.infrastructure.controller;

import mobify.app.security.domain.contracts.PrivateContract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateControllerImpl implements PrivateContract.PrivateController {
    private PrivateContract.PrivateUseCase privateUseCase;

    public PrivateControllerImpl(PrivateContract.PrivateUseCase privateUseCase) {
        this.privateUseCase = privateUseCase;
        privateUseCase.setController(this);
    }

    @GetMapping("/admin")
    public String admin() {
        return privateUseCase.admin();
    }

    @GetMapping("/user")
    public String user() {
        return privateUseCase.user();
    }

    @Override
    public String adminPath() {
        return "/private/admin";
    }

    @Override
    public String userPath() {
        return "/private/user";
    }
}
