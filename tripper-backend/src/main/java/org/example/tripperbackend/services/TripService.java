package org.example.tripperbackend.services;

import org.example.tripperbackend.models.Trip;

import java.util.List;

public interface TripService {

    List<Trip> findAllVisibleTrips();

    Trip findTripById(String tripId);

    List<Trip> findTripsByOwner(String userId);

    Trip createTrip(Trip trip, String userId);

    Trip updateTrip(Trip trip, String userId) throws Exception;

    void deleteTrip(String tripId, String userId) throws Exception;
}
