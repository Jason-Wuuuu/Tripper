package org.example.tripperbackend.services;

import org.example.tripperbackend.models.Stop;
import org.example.tripperbackend.repositories.StopRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StopServiceImpl implements StopService {

    private final StopRepository stopRepository;

    @Autowired
    public StopServiceImpl(StopRepository stopRepository) {
        this.stopRepository = stopRepository;
    }

    @Override
    public List<Stop> findAllVisibleStops() {
        return stopRepository.findByVisibility(true);
    }

    @Override
    public Stop findStopById(String stopId) {
        return stopRepository.findById(stopId)
                .filter(Stop::getVisibility)
                .orElse(null);
    }

    @Override
    public List<Stop> findStopsByOwner(String userId) {
        return stopRepository.findByOwner(userId);
    }

    @Override
    public Stop createStop(Stop stop, String userId) {
        stop.setOwner(userId);
        return stopRepository.save(stop);
    }

    @Override
    public Stop updateStop(Stop stop, String userId) throws Exception {
        // Check if the current user is the owner of the trip before updating
        Stop existingStop = findStopById(stop.getStopId());
        if (!existingStop.getOwner().equals(userId) && !existingStop.getCollaborators().contains(userId)) {
            throw new Exception("Unauthorized to update this stop.");
        }

        return stopRepository.save(stop);
    }

    @Override
    public void deleteStop(String stopId, String userId) throws Exception {
        // Check if the current user is the owner of the trip before deleting
        Stop stop = findStopById(stopId);
        if (!stop.getOwner().equals(userId)) {
            throw new Exception("Unauthorized to delete this stop.");
        }

        stopRepository.delete(stop);
    }
}
