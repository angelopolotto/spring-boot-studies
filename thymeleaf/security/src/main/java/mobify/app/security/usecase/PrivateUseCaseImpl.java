package mobify.app.security.usecase;

import mobify.app.security.domain.contracts.PrivateContract;
import org.springframework.stereotype.Service;

@Service
public class PrivateUseCaseImpl implements PrivateContract.PrivateUseCase {
    private PrivateContract.PrivateController privateController;

    @Override
    public void setController(PrivateContract.PrivateController privateController) {
        this.privateController = privateController;
    }

    @Override
    public String admin() {
        return privateController.adminPath();
    }

    @Override
    public String user() {
        return privateController.userPath();
    }
}
