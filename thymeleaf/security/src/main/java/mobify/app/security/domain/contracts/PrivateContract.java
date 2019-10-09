package mobify.app.security.domain.contracts;

public interface PrivateContract {
    interface PrivateController {

        String adminPath();

        String userPath();
    }

    interface PrivateUseCase {

        void setController(PrivateController privateController);

        String admin();

        String user();
    }
}
