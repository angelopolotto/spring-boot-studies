package mobify.app.security.usecase;

import mobify.app.security.domain.contracts.ErrorContract;
import org.springframework.stereotype.Service;

@Service
public class ErrorUseCaseImpl implements ErrorContract.ErrorUseCase {
    private ErrorContract.ErrorController errorController;

    @Override
    public String error403() {
        return errorController.error403Path();
    }

    @Override
    public void setController(ErrorContract.ErrorController errorController) {
        this.errorController = errorController;
    }
}
