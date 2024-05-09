package org.example.tripperbackend.services;

import org.example.tripperbackend.models.Schedule;
import org.example.tripperbackend.models.Stop;
import org.example.tripperbackend.models.Trip;
import org.example.tripperbackend.repositories.TripRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    private final TripRepository tripRepository;

    public TripService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip createTrip(Trip trip) {
        return tripRepository.save(trip);
    }

    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

    public Optional<Trip> getTrip(String userId, String tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));

        // public
        if (trip.isVisibility()){
            return Optional.of(trip);
        }

        // Check if the user is an owner of the trip if private
        if (trip.getOwners().contains(userId)) {
            return Optional.of(trip);
        } else {
            throw new RuntimeException("Unauthorized access");
        }
    }

    public Trip updateTrip(String userId, Trip trip) {
        validateUserIsOwner(userId, trip.getId());

        return tripRepository.save(trip);
    }

    public void deleteTrip(String userId, String tripId) {
        validateUserIsOwner(userId, tripId);

        tripRepository.deleteById(tripId);
    }

    public Trip addSchedule(String userId, String tripId, Schedule schedule) {
        validateUserIsOwner(userId, tripId);

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.addSchedule(schedule);

        return tripRepository.save(trip);
    }

    public Trip removeSchedule(String userId, String tripId, String scheduleId) {
        validateUserIsOwner(userId, tripId);

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.getSchedules().removeIf(s -> s.getId().equals(scheduleId));

        return tripRepository.save(trip);
    }

    public Trip addStop(String userId, String tripId, String scheduleId, Stop stop) {
        validateUserIsOwner(userId, tripId);

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.getSchedules().stream()
                .filter(s -> s.getId().equals(scheduleId))
                .findFirst()
                .ifPresent(schedule -> schedule.addStop(stop));

        return tripRepository.save(trip);
    }

    public Trip removeStop(String userId, String tripId, String scheduleId, String stopId) {
        validateUserIsOwner(userId, tripId);

        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        trip.getSchedules().stream()
                .filter(s -> s.getId().equals(scheduleId))
                .findFirst()
                .ifPresent(schedule -> schedule.getStops().removeIf(stop -> stop.getId().equals(stopId)));

        return tripRepository.save(trip);
    }

    private void validateUserIsOwner(String userId, String tripId) {
        Trip trip = tripRepository.findById(tripId).orElseThrow(() -> new RuntimeException("Trip not found"));
        if (!trip.getOwners().contains(userId)) {
            throw new RuntimeException("Unauthorized access");
        }
    }
}
