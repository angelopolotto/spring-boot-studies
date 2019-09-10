package mobify.app.security.infrastructure.jparepository;

import mobify.app.security.domain.entity.Role;
import mobify.app.security.domain.interfaces.RoleRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleJpaRepository extends JpaRepository<Role, Integer>, RoleRepository {
    Role findByRole(String role);
}
