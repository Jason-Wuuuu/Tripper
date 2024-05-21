package org.example.tripperbackend.controllers;

import org.example.tripperbackend.models.User;
import org.example.tripperbackend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUserProfile(@RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7);  // Remove 'Bearer ' from the token
            User user = userService.getProfile(token);

            return ResponseEntity.ok(user);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateUserProfile(@RequestHeader("Authorization") String token, @RequestBody User user) {
        try {
            token = token.substring(7);  // Remove 'Bearer ' from the token
            User updatedUser = userService.updateProfile(token, user);

            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteUser(@RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7);  // Remove 'Bearer ' from the token
            userService.deleteUser(token);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()), HttpStatus.UNAUTHORIZED);
        }
    }
}
