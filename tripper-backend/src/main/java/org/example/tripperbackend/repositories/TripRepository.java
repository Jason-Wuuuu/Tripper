package org.example.tripperbackend.repositories;

import org.example.tripperbackend.models.Trip;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TripRepository extends MongoRepository<Trip, String> {
     List<Trip> findByOwner(String owner);
     List<Trip> findByVisibility(Boolean visibility);
}
