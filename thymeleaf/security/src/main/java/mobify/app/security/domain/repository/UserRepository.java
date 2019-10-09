package mobify.app.security.domain.repository;

import mobify.app.security.domain.entity.User;

import java.util.List;

public interface UserRepository {
    User findByEmail(String email);

    User save(User user);

    List<User> findAll();
}
