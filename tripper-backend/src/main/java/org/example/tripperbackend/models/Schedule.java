package org.example.tripperbackend.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Schedule {

    @Id
    private String id;
    private Date date;
    private List<Stop> stops;

    public Schedule(Date date, List<Stop> stops) {
        this.id = String.valueOf(new ObjectId()); // Generate ObjectId for the schedule
        this.date = date;
        this.stops = Objects.requireNonNullElseGet(stops, ArrayList::new);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public void setStops(List<Stop> stops) {
        this.stops = stops;
    }

    public void addStop(Stop stop) {
        this.stops.add(stop);
    }

    public void removeStop(Stop stop) {
        this.stops.remove(stop);
    }
}
