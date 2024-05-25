package org.example.tripperbackend.controllers;

import org.example.tripperbackend.models.Schedule;
import org.example.tripperbackend.security.JwtUtil;
import org.example.tripperbackend.services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final JwtUtil jwtUtil;

    @Autowired
    public ScheduleController(ScheduleService scheduleService, JwtUtil jwtUtil) {
        this.scheduleService = scheduleService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    public ResponseEntity<?> findAllVisibleSchedules() {
        try {
            List<Schedule> schedules = scheduleService.findAllVisibleSchedules();
            return ResponseEntity.ok(schedules);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{scheduleId}")
    public ResponseEntity<?> findScheduleById(@PathVariable String scheduleId) {
        try {
            Schedule schedule = scheduleService.findScheduleById(scheduleId);
            if (schedule == null) {
                return new ResponseEntity<>("Schedule not found or not visible", HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(schedule);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/owner/{userId}")
    public ResponseEntity<?> findSchedulesByOwner(@PathVariable String userId) {
        try {
            List<Schedule> schedules = scheduleService.findSchedulesByOwner(userId);
            return ResponseEntity.ok(schedules);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/mine")
    public ResponseEntity<?> findMySchedules(@RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7);
            String userId = jwtUtil.extractUserId(token);

            List<Schedule> schedules = scheduleService.findSchedulesByOwner(userId);
            return ResponseEntity.ok(schedules);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSchedule(@RequestBody Schedule schedule, @RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7);
            String userId = jwtUtil.extractUserId(token);

            Schedule newSchedule = scheduleService.createSchedule(schedule, userId);
            return new ResponseEntity<>(newSchedule, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSchedule(@RequestBody Schedule schedule, @RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7);
            String userId = jwtUtil.extractUserId(token);

            Schedule updatedSchedule = scheduleService.updateSchedule(schedule, userId);
            return ResponseEntity.ok(updatedSchedule);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<?> deleteSchedule(@PathVariable String scheduleId, @RequestHeader("Authorization") String token) {
        try {
            token = token.substring(7);
            String userId = jwtUtil.extractUserId(token);

            scheduleService.deleteSchedule(scheduleId, userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
