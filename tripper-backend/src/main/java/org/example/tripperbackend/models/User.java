package org.example.tripperbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Users")
public class User {
    @Id
    private String id;

    @Field
    private String username;

    @Field
    private String email;

    @Field
    private String password;

    @Field
    private List<String> savedTrips; // Array of TripIds

    // Constructors
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.savedTrips = new ArrayList<>(); // Initialize with an empty list
    }

    // Getters, and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getSavedTrips() {
        return savedTrips;
    }

    public void setSavedTrips(List<String> savedTrips) {
        this.savedTrips = savedTrips;
    }
}
