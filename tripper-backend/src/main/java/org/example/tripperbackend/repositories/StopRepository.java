package org.example.tripperbackend.repositories;

import org.example.tripperbackend.models.Stop;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StopRepository extends MongoRepository<Stop, String> {
    List<Stop> findByOwner(String owner);
    List<Stop> findByVisibility(Boolean visibility);
    List<Stop> findByLocation(GeoJsonPoint location);
    // List<Stop> findBySearchTerm(String searchTerm);
}
