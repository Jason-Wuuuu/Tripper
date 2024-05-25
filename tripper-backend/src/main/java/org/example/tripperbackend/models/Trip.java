package org.example.tripperbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.*;

@Document(collection = "Trips")
public class Trip {
    @Id
    private String tripId;

    @Field
    private String owner;

    @Field
    private List<String> collaborators = new ArrayList<>();

    @Field
    private String title;

    @Field
    private String description = "";

    @Field
    private Date startDate;

    @Field
    private Date endDate;

    @Field
    private Boolean visibility = true;

    @Field
    private Map<Integer, String> schedules = new HashMap<>();

    @Field
    private List<String> notAssignedSchedules = new ArrayList<>();

    // Constructor with required fields
    public Trip(String title, Date startDate, Date endDate) {
        this.title = Objects.requireNonNull(title, "Title cannot be null");
        this.startDate = Objects.requireNonNull(startDate, "Start date cannot be null");
        this.endDate = Objects.requireNonNull(endDate, "End date cannot be null");
        initializeDateToScheduleMap();
    }

    private void initializeDateToScheduleMap() {
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);
        Calendar end = Calendar.getInstance();
        end.setTime(endDate);

        int dayIndex = 0; // Initialize day index starting from 0
        for (Date date = start.getTime(); !start.after(end); start.add(Calendar.DATE, 1), date = start.getTime()) {
            schedules.put(dayIndex++, null); // Use dayIndex as the key and increment it each day
        }
    }

    // Getters and Setters
    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getVisibility() {
        return visibility;
    }

    public void setVisibility(Boolean visibility) {
        this.visibility = visibility;
    }

    public Map<Integer, String> getSchedules() {
        return schedules;
    }

    public void setSchedules(Map<Integer, String> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(int day, String scheduleId) {
        if (schedules.containsKey(day)) {
            schedules.put(day, scheduleId);
        }
    }
    public List<String> getNotAssignedSchedules() {
        return notAssignedSchedules;
    }

    public void setNotAssignedSchedules(List<String> notAssignedSchedules) {
        this.notAssignedSchedules = notAssignedSchedules;
    }

}
