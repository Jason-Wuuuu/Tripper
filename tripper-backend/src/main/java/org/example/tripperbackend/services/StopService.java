package org.example.tripperbackend.services;

import org.example.tripperbackend.models.Stop;

import java.util.List;

public interface StopService {

    List<Stop> findAllVisibleStops();

    Stop findStopById(String stopId);

    List<Stop> findStopsByOwner(String userId);

    Stop createStop(Stop stop, String userId);

    Stop updateStop(Stop stop, String userId) throws Exception;

    void deleteStop(String stopId, String userId) throws Exception;
}
