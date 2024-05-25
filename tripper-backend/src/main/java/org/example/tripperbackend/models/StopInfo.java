package org.example.tripperbackend.models;

public class StopInfo {
    private String stopId;
    private int duration; // Duration in minutes

    // Constructors
    public StopInfo(String stopId, int duration) {
        this.stopId = stopId;
        this.duration = duration;
    }

    // Getters and Setters
    public String getStopId() {
        return stopId;
    }

    public void setStopId(String stopId) {
        this.stopId = stopId;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
