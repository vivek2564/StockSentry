package com.vivek.stocksentry.controller;
import com.vivek.stocksentry.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Map<String, String> request) {
        String token = authService.register(
                request.get("username"),
                request.get("email"),
                request.get("password"),
                request.get("phoneNumber")
        );
        return ResponseEntity.ok(Map.of("token", token));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {
        String token = authService.login(
                request.get("username"),
                request.get("password")
        );
        return ResponseEntity.ok(Map.of("token", token));
    }
}
