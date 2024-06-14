package harena.dev.banking.controller;

import harena.dev.banking.config.CustomRole;
import harena.dev.banking.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/test")
public class TestSecurityController {


    private final JwtUtil jwtUtil;

    public TestSecurityController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }


    @GetMapping("/admin-action")
    @CustomRole({"admin"})
    public String adminAction() {
        return "This is an admin action";
    }

    @GetMapping("/user-action")
    @CustomRole({"user", "admin"})
    public String userAction() {
        return "This is a user action";
    }

    @GetMapping("/generate-token-admin")
    public String generateTokenAdmin() {
        String userId = "user123";
        String role = "admin";
        return jwtUtil.generateToken(userId, role);
    }
    @GetMapping("/generate-token-user")
    public String generateTokenUser() {
        String userId = "2";
        String role = "user";
        return jwtUtil.generateToken(userId, role);
    }
}
