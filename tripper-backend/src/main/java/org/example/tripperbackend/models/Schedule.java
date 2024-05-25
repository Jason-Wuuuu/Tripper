package org.example.tripperbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document(collection = "Schedules")
public class Schedule {
    @Id
    private String scheduleId;

    @Field
    private String owner;

    @Field
    private List<String> collaborators = new ArrayList<>();

    @Field
    private Boolean visibility = true;

    @Field
    private Map<String, StopInfo> stops = new HashMap<>();

    @Field
    private List<String> notAssignedStops = new ArrayList<>();

    // Getters and Setters
    public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public List<String> getCollaborators() {
        return collaborators;
    }

    public void setCollaborators(List<String> collaborators) {
        this.collaborators = collaborators;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Map<String, StopInfo> getStops() {
        return stops;
    }

    public void setStops(Map<String, StopInfo> stops) {
        this.stops = stops;
    }

    public List<String> getNotAssignedStops() {
        return notAssignedStops;
    }

    public void setNotAssignedStops(List<String> notAssignedStops) {
        this.notAssignedStops = notAssignedStops;
    }
}
