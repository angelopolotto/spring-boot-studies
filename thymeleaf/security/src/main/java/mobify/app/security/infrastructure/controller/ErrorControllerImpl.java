package mobify.app.security.infrastructure.controller;

import mobify.app.security.domain.contracts.ErrorContract;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorControllerImpl implements ErrorContract.ErrorController {
    private ErrorContract.ErrorUseCase errorUseCase;

    public ErrorControllerImpl(ErrorContract.ErrorUseCase errorUseCase) {
        this.errorUseCase = errorUseCase;
        errorUseCase.setController(this);
    }

    @GetMapping("/403")
    public String error403() {
        return errorUseCase.error403();
    }

    @Override
    public String error403Path() {
        return "/error/403";
    }
}
