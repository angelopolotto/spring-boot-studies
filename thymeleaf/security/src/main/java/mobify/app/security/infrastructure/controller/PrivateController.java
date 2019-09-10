package mobify.app.security.infrastructure.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PrivateController {
    @GetMapping("/admin")
    public String admin() {
        return "/private/admin";
    }

    @GetMapping("/user")
    public String user() {
        return "/private/user";
    }
}
