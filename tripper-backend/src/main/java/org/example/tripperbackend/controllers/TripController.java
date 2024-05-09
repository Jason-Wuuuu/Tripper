package org.example.tripperbackend.controllers;

import org.example.tripperbackend.models.Schedule;
import org.example.tripperbackend.models.Stop;
import org.example.tripperbackend.models.Trip;
import org.example.tripperbackend.security.CustomUserDetails;
import org.example.tripperbackend.services.TripService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;

    public TripController(TripService tripService) {
        this.tripService = tripService;
    }

    // Create a new trip
    @PostMapping
    public ResponseEntity<Trip> createTrip(Authentication authentication, @RequestBody Trip trip) {
        String userId = getUserIdFromAuthentication(authentication);
        trip.addOwner(userId);
        Trip createdTrip = tripService.createTrip(trip);
        return new ResponseEntity<>(createdTrip, HttpStatus.CREATED);
    }

    // Retrieve all trips
    @GetMapping
    public ResponseEntity<List<Trip>> getAllTrips() {
        List<Trip> trips = tripService.getAllTrips();
        return new ResponseEntity<>(trips, HttpStatus.OK);
    }

    // Retrieve a single trip by ID
    @GetMapping("/{tripId}")
    public ResponseEntity<Trip> getTrip(Authentication authentication, @PathVariable String tripId) {
        String userId = null;
        if (authentication != null) {
            userId = getUserIdFromAuthentication(authentication);
        }

        try {
            Trip trip = tripService.getTrip(userId, tripId).orElse(null);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // Update a trip
    @PutMapping("/{tripId}")
    public ResponseEntity<Trip> updateTrip(Authentication authentication, @PathVariable String tripId, @RequestBody Trip trip) {
        String userId = getUserIdFromAuthentication(authentication);
        try {
            Trip updatedTrip = tripService.updateTrip(userId, trip);
            return new ResponseEntity<>(updatedTrip, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // Delete a trip
    @DeleteMapping("/{tripId}")
    public ResponseEntity<Void> deleteTrip(Authentication authentication, @PathVariable String tripId) {
        String userId = getUserIdFromAuthentication(authentication);
        try {
            tripService.deleteTrip(userId, tripId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // Add a schedule to a trip
    @PostMapping("/{tripId}/schedules")
    public ResponseEntity<Trip> addSchedule(Authentication authentication, @PathVariable String tripId, @RequestBody Schedule schedule) {
        String userId = getUserIdFromAuthentication(authentication);
        try {
            Trip trip = tripService.addSchedule(userId, tripId, schedule);
            return new ResponseEntity<>(trip, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // Remove a schedule from a trip
    @DeleteMapping("/{tripId}/schedules/{scheduleId}")
    public ResponseEntity<Trip> removeSchedule(Authentication authentication, @PathVariable String tripId, @PathVariable String scheduleId) {
        String userId = getUserIdFromAuthentication(authentication);
        try {
            Trip trip = tripService.removeSchedule(userId, tripId, scheduleId);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // Add a stop to a schedule
    @PostMapping("/{tripId}/schedules/{scheduleId}/stops")
    public ResponseEntity<Trip> addStop(Authentication authentication, @PathVariable String tripId, @PathVariable String scheduleId, @RequestBody Stop stop) {
        String userId = getUserIdFromAuthentication(authentication);
        try {
            Trip trip = tripService.addStop(userId, tripId, scheduleId, stop);
            return new ResponseEntity<>(trip, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // Remove a stop from a schedule
    @DeleteMapping("/{tripId}/schedules/{scheduleId}/stops/{stopId}")
    public ResponseEntity<Trip> removeStop(Authentication authentication, @PathVariable String tripId, @PathVariable String scheduleId, @PathVariable String stopId) {
        String userId = getUserIdFromAuthentication(authentication);
        try {
            Trip trip = tripService.removeStop(userId, tripId, scheduleId, stopId);
            return new ResponseEntity<>(trip, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    // Helper method to extract userId from Authentication object
    private String getUserIdFromAuthentication(Authentication authentication) {
        if (authentication.getPrincipal() instanceof CustomUserDetails userDetails) {
            return userDetails.getUserId();
        } else {
            throw new IllegalArgumentException("User not authenticated");
        }
    }
}
