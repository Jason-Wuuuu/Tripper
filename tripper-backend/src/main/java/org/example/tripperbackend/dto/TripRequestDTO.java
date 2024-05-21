package org.example.tripperbackend.dto;

import java.util.Date;

public class TripRequestDTO {
    private String tripId;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private Boolean visibility;

    // Getters and setters
    public String getTripId() {
        return tripId;
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
}
