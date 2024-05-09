package org.example.tripperbackend.repositories;

import org.example.tripperbackend.models.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {

    // Here you can define any custom query methods you might need
    List<Trip> findByOwnersContaining(String ownerId);
    List<Trip> findByVisibilityTrue();

}
