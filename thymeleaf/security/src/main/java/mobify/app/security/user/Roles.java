package mobify.app.security.user;

public enum Roles {
    ADMIN("ADMIN", "/admin/**"),
    USER("USER", "/user/**");

    public String role;
    public String roleAuthority;
    public String authorizedUrl;

    Roles(String role, String authorizedUrl) {
        this.role = role;
        this.roleAuthority = "ROLE_" + role;
        this.authorizedUrl = authorizedUrl;
    }
}
