package org.example.tripperbackend.services;

import org.example.tripperbackend.models.Trip;
import org.example.tripperbackend.repositories.TripRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripServiceImpl implements TripService {

    private final TripRepository tripRepository;

    @Autowired
    public TripServiceImpl(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Override
    public List<Trip> findAllVisibleTrips() {
        // Fetch all trips that are marked as visible
        return tripRepository.findByVisibility(true);
    }

    @Override
    public Trip findTripById(String tripId) {
        return tripRepository.findById(tripId)
                .filter(Trip::getVisibility)
                .orElse(null);
    }

    @Override
    public List<Trip> findTripsByOwner(String ownerId) {
        // Fetch all trips by a specific owner
        return tripRepository.findByOwner(ownerId);
    }

    @Override
    public Trip createTrip(Trip trip, String userId) {
        trip.setOwner(userId);
        return tripRepository.save(trip);
    }

    @Override
    public Trip updateTrip(Trip trip, String userId) throws Exception {
        // Check if the current user is the owner of the trip before updating
        Trip existingTrip = findTripById(trip.getTripId());
        if (!existingTrip.getOwner().equals(userId) && !existingTrip.getCollaborators().contains(userId)) {
            throw new Exception("Unauthorized to update this trip.");
        }

        return tripRepository.save(trip);
    }


    @Override
    public void deleteTrip(String tripId, String userId) throws Exception {
        // Check if the current user is the owner of the trip before deleting
        Trip trip = findTripById(tripId);
        if (!trip.getOwner().equals(userId)) {
            throw new Exception("Unauthorized to delete this trip.");
        }

        tripRepository.delete(trip);
    }
}
