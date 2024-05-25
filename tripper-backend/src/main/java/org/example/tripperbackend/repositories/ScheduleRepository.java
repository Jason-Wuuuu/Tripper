package org.example.tripperbackend.repositories;

import org.example.tripperbackend.models.Schedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ScheduleRepository extends MongoRepository<Schedule, String> {
    List<Schedule> findByOwner(String owner);
    List<Schedule> findByVisibility(Boolean visibility);
}
