package org.example.tripperbackend.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Document(collection = "trips")
public class Trip {

    @Id
    private String id;
    private String title;
    private List<String> owners; // Stores user IDs
    private boolean visibility;
    private Date startDate;
    private Date endDate;
    private List<Schedule> schedules;

    public Trip(String title, boolean visibility, Date startDate, Date endDate, List<Schedule> schedules) {
        this.title = title;
        this.owners = new ArrayList<>();
        this.visibility = visibility;
        this.startDate = startDate;
        this.endDate = endDate;
        this.schedules = Objects.requireNonNullElseGet(schedules, ArrayList::new);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getOwners() {
        return owners;
    }

    public void setOwners(List<String> owners) {
        this.owners = owners;
    }

    public void addOwner(String userId) {
        this.owners.add(userId);
    }

    public void removeOwner(String userId) {
        this.owners.remove(userId);
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
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

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
    }

    public void removeSchedule(Schedule schedule) {
        this.schedules.remove(schedule);
    }
}
