package org.example.tripperbackend.services;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.example.tripperbackend.models.User;
import org.example.tripperbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Fetch user profile by user ID
    public User getUserById(@NotBlank String id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }

    // Update user password by user ID
    public User updateUserById(@NotBlank String id,
                               @NotBlank @Size(min = 3, max = 100) String newUsername,
                               @NotBlank @Size(min = 8, max = 100) String newPassword) {
        User user = getUserById(id);

        if (newUsername != null && !newUsername.isEmpty()) {
            user.setUsername(newUsername);
        }
        if (newPassword != null && !newPassword.isEmpty()) {
            user.setPassword(passwordEncoder.encode(newPassword));
        }

        userRepository.save(user);
        return user;
    }

    // Delete user by user ID
    public void deleteUserById(@NotBlank String id) {
        userRepository.delete(getUserById(id));
    }
}
