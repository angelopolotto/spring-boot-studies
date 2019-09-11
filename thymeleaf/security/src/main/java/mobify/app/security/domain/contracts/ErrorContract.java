package mobify.app.security.domain.contracts;

public interface ErrorContract {
    interface ErrorController {
        String error403Path();
    }

    interface ErrorUseCase {
        String error403();

        void setController(ErrorController errorController);
    }
}
