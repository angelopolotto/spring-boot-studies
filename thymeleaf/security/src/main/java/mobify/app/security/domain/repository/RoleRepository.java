package mobify.app.security.domain.repository;

import mobify.app.security.domain.entity.Role;

public interface RoleRepository {
    Role findByRole(String role);
}
