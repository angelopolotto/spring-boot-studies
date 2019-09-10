package mobify.app.security.domain.interfaces;

import mobify.app.security.domain.entity.User;

import java.util.List;

public interface PublicUseCase {
    User findUserByEmail(String email);

    User saveUser(User user);

    List<User> findAll();
}
