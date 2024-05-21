package org.example.tripperbackend.services;

import org.example.tripperbackend.models.User;
import org.example.tripperbackend.repositories.UserRepository;
import org.example.tripperbackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(String username, String email, String password) throws Exception {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new Exception("Username already exists.");
        }

        if (userRepository.findByEmail(email).isPresent()) {
            throw new Exception("Email already exists.");
        }

        String encodedPassword = passwordEncoder.encode(password);
        User newUser = new User(username, email, encodedPassword);

        return userRepository.save(newUser);
    }

    @Override
    public String login(String identity, String password) throws Exception {
        Optional<User> userOptional;

        if (identity.contains("@")) {
            userOptional = userRepository.findByEmail(identity);
        } else {
            userOptional = userRepository.findByUsername(identity);
        }

        User user = userOptional.orElseThrow(() -> new Exception("User not found."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("Invalid password.");
        }

        return jwtUtil.generateToken(user.getId());
    }
}
