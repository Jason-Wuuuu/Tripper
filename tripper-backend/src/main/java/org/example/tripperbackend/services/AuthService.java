package org.example.tripperbackend.services;

import org.example.tripperbackend.models.User;

public interface AuthService {
    User register(String username, String email, String password) throws Exception;

    String login(String identity, String password) throws Exception;
}
