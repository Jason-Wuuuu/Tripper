package org.example.tripperbackend.services;

import org.example.tripperbackend.models.Role;
import org.example.tripperbackend.models.User;
import org.example.tripperbackend.repositories.RoleRepository;
import org.example.tripperbackend.repositories.UserRepository;
import org.example.tripperbackend.security.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder,
                       JwtTokenProvider tokenProvider,
                       AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.tokenProvider = tokenProvider;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user with a default role.
     */
    @Transactional
    public User register(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            throw new IllegalStateException("Username already exists");
        }

        User user = new User(username, passwordEncoder.encode(password));
        Role userRole = roleRepository.findByName("USER").orElseGet(() -> {
            Role newRole = new Role("USER");
            roleRepository.save(newRole);
            return newRole;
        });

        user.setRoles(Collections.singleton(userRole));
        userRepository.save(user);
        return user;
    }

    /**
     * Authenticates a user and returns a JWT token.
     */
    public String login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        // Now setting the authentication in the security context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Generate JWT token
        return tokenProvider.generateToken(authentication);
    }
}