package org.example.tripperbackend.services;

import org.example.tripperbackend.models.User;

public interface UserService {
    User getUser(String userId) throws Exception;

    User getProfile(String token) throws Exception;

    User updateProfile(String token, User updatedUser) throws Exception;

    void deleteUser(String token) throws Exception;
}
