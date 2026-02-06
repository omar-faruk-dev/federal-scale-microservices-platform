package gov.platform.authservice.controller;

import java.time.Instant;
import java.util.Map;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuditController {

    @GetMapping("/me")
    @Operation(summary = "Inspect current authenticated principal and roles")
    public Map<String, Object> me(Authentication authentication) {
        return Map.of(
            "username", authentication.getName(),
            "authorities", authentication.getAuthorities(),
            "timestamp", Instant.now().toString(),
            "auditEvent", "AUTH_PRINCIPAL_INSPECTED"
        );
    }
}
