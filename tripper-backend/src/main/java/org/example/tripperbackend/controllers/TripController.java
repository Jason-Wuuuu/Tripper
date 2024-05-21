package org.example.tripperbackend.controllers;

import org.example.tripperbackend.models.Trip;
import org.example.tripperbackend.security.JwtUtil;
import org.example.tripperbackend.services.TripService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
public class TripController {

    private final TripService tripService;
    private final JwtUtil jwtUtil;

    @Autowired
    public TripController(TripService tripService, JwtUtil jwtUtil) {
        this.tripService = tripService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<?> findAllVisibleTrips() {
        try {
            List<Trip> trips = tripService.findAllVisibleTrips();
            return ResponseEntity.ok(trips);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/{tripId}")
    public ResponseEntity<?> findTripById(@PathVariable String tripId) {
        try {
            Trip trip = tripService.findTripById(tripId);
            if (trip == null) {
                return new ResponseEntity<>("Trip not found or not visible", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(trip);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/owner/{userId}")
    public ResponseEntity<?> findTripsByOwner(@PathVariable String userId) {
        try {
            List<Trip> trips = tripService.findTripsByOwner(userId);
            return ResponseEntity.ok(trips);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mine")
    public ResponseEntity<?> findMyTrips(@RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7); // Remove 'Bearer ' from the token
            String userId = jwtUtil.extractUserId(token);

            List<Trip> trips = tripService.findTripsByOwner(userId);
            return ResponseEntity.ok(trips);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createTrip(@RequestBody Trip trip, @RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7); // Remove 'Bearer ' from the token
            String userId = jwtUtil.extractUserId(token);

            Trip newTrip = tripService.createTrip(trip, userId);
            return new ResponseEntity<>(newTrip, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateTrip(@RequestBody Trip trip, @RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7); // Remove 'Bearer ' from the token
            String userId = jwtUtil.extractUserId(token);

            Trip updatedTrip = tripService.updateTrip(trip, userId);
            return ResponseEntity.ok(updatedTrip);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

    @DeleteMapping("/delete/{tripId}")
    public ResponseEntity<?> deleteTrip(@PathVariable String tripId, @RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7); // Remove 'Bearer ' from the token
            String userId = jwtUtil.extractUserId(token);

            tripService.deleteTrip(tripId, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
