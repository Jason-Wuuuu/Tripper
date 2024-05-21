package org.example.tripperbackend.controllers;

import org.example.tripperbackend.dto.LoginRequestDTO;
import org.example.tripperbackend.dto.RegistrationRequestDTO;
import org.example.tripperbackend.models.User;
import org.example.tripperbackend.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegistrationRequestDTO registrationRequest) {
        try {
            User user = authService.register(registrationRequest.getUsername(), registrationRequest.getEmail(), registrationRequest.getPassword());
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO loginRequest) {
        try {
            String token = authService.login(loginRequest.getIdentity(), loginRequest.getPassword());
            return ResponseEntity.ok(Collections.singletonMap("token", token));
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

}



