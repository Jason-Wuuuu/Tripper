package org.example.tripperbackend.services;

import org.example.tripperbackend.models.User;
import org.example.tripperbackend.repositories.UserRepository;
import org.example.tripperbackend.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User getUser(String userId) throws Exception {
        return userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
    }

    @Override
    public User getProfile(String token) throws Exception {
        String userId = jwtUtil.extractUserId(token);
        return getUser(userId);
    }

    @Transactional
    @Override
    public User updateProfile(String token, User updatedUser) throws Exception {
        User user = getProfile(token);

        //if (!jwtUtil.validateToken(token)) {
        //    throw new Exception("Invalid or expired token.");
        //}

        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());
        user.setPassword(passwordEncoder.encode(updatedUser.getPassword()));

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String token) throws Exception {
        User user = getProfile(token);
        userRepository.delete(user);
    }
}
