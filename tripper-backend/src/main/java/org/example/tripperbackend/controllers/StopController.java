package org.example.tripperbackend.controllers;

import org.example.tripperbackend.models.Stop;
import org.example.tripperbackend.security.JwtUtil;
import org.example.tripperbackend.services.StopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/stops")
public class StopController {

    private final StopService stopService;
    private final JwtUtil jwtUtil;

    @Autowired
    public StopController(StopService stopService, JwtUtil jwtUtil) {
        this.stopService = stopService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<?> findAllVisibleStops(){
        try {
            List<Stop> stops = stopService.findAllVisibleStops();
            return ResponseEntity.ok(stops);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{stopId}")
    public ResponseEntity<?> findStopById(@PathVariable String stopId){
        try {
            Stop stop = stopService.findStopById(stopId);
            if (stop == null) {
                return new ResponseEntity<>("Stop not found or not visible", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(stop);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStop(@RequestBody Stop stop, @RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7); // Remove 'Bearer ' from the token
            String userId = jwtUtil.extractUserId(token);

            Stop newStop = stopService.createStop(stop, userId);
            return new ResponseEntity<>(newStop, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}