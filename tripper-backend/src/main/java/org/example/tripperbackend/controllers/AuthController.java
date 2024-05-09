package org.example.tripperbackend.controllers;

import org.example.tripperbackend.dto.LoginResponse;
import org.example.tripperbackend.dto.RegisterResponse;
import org.example.tripperbackend.dto.UserRequest;
import org.example.tripperbackend.models.User;
import org.example.tripperbackend.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser( @RequestBody @Validated UserRequest newUser) {
        try {
            User user = authService.register(newUser.getUsername(), newUser.getPassword());
            RegisterResponse response = new RegisterResponse(user.getUsername());
            return ResponseEntity.ok(response);
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser( @RequestBody @Validated UserRequest user) {
        try {
            String token = authService.login(user.getUsername(), user.getPassword());
            LoginResponse response = new LoginResponse(token);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }
}
