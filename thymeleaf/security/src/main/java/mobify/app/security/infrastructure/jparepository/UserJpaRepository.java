package mobify.app.security.infrastructure.jparepository;

import mobify.app.security.domain.entity.User;
import mobify.app.security.domain.repository.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends JpaRepository<User, Long>, UserRepository {
    User findByEmail(String email);

    @SuppressWarnings("unchecked")
    User save(User user);

    List<User> findAll();
}
