package mobify.app.security.config;

public enum RoleConfig {
    ADMIN("ADMIN", "/admin/**"),
    USER("USER", "/user/**");

    public String role;
    public String roleAuthority;
    public String authorizedUrl;

    RoleConfig(String role, String authorizedUrl) {
        this.role = role;
        this.roleAuthority = "ROLE_" + role;
        this.authorizedUrl = authorizedUrl;
    }
}
