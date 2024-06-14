package harena.dev.banking.config;

import io.jsonwebtoken.Claims;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import jakarta.servlet.http.HttpServletRequest;

@Aspect
@Component
public class CustomRoleAspect {

    @Autowired
    private JwtUtil jwtUtil;

    @Before("@annotation(customRole)") // Pointcut pour les méthodes annotées avec @CustomRole
    public void checkCustomRole(CustomRole customRole) {
        String token = extractToken();
        if (token == null) {
            throw new RuntimeException("Token missing");
        }

        Claims claims = jwtUtil.extractAllClaims(token);
        String role = jwtUtil.extractRole(token);

        if (!isRoleAllowed(role, customRole.value())) {
            throw new RuntimeException(role+ " is not authorized for this action");
        }
    }

    private String extractToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Retourne seulement le token sans le préfixe "Bearer "
        }
        return null;
    }

    private boolean isRoleAllowed(String userRole, String[] allowedRoles) {
        for (String role : allowedRoles) {
            if (role.equals(userRole)) {
                return true;
            }
        }
        return false;
    }
}
