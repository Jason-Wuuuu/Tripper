package org.example.tripperbackend.controllers;

import org.example.tripperbackend.dto.UserRequest;
import org.example.tripperbackend.models.User;
import org.example.tripperbackend.security.CustomUserDetails;
import org.example.tripperbackend.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            String userId = userDetails.getUserId();
            User user = userService.getUserById(userId);

            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @PutMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateProfile(Authentication authentication, @RequestBody @Validated UserRequest updateRequest) {
        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            String userId = userDetails.getUserId();

            if (updateRequest.getUsername() == null && updateRequest.getPassword() == null) {
                return ResponseEntity.badRequest().body("At least one of newUsername or newPassword must be provided.");
            }

            User updatedUser = userService.updateUserById(userId, updateRequest.getUsername(), updateRequest.getPassword());

            return ResponseEntity.ok(updatedUser);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> deleteUser(Authentication authentication) {
        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            String userId = userDetails.getUserId();
            userService.deleteUserById(userId);

            return ResponseEntity.ok("User deleted successfully");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Unauthorized");
        }
    }
}
