package org.example.tripperbackend.models;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Stop {

    @Id
    private String id;
    private Date startTime;
    private Date endTime;

    //  StopDetails
    private String title;
    private List<String> tags;
    private String description;
    private String location;
    private String externalLink;
    private List<String> media;
    private List<String> notes;

    public Stop(Date startTime, Date endTime, String title, List<String> tags, String description, String location, String externalLink, List<String> media, List<String> notes) {
        this.id = String.valueOf(new ObjectId()); // Generate ObjectId for the schedule
        this.startTime = startTime;
        this.endTime = endTime;
        this.title = title;
        this.tags = Objects.requireNonNullElseGet(tags, ArrayList::new);
        this.description = description;
        this.location = location;
        this.externalLink = externalLink;
        this.media = Objects.requireNonNullElseGet(media, ArrayList::new);
        this.notes = Objects.requireNonNullElseGet(notes, ArrayList::new);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public void addTag(String tag) {
        this.tags.add(tag);
    }

    public void removeTag(String tag) {
        this.tags.remove(tag);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public List<String> getMedia() {
        return media;
    }

    public void setMedia(List<String> media) {
        this.media = media;
    }

    public void addMedia(String media) {
        this.media.add(media);
    }

    public void removeMedia(String media) {
        this.media.remove(media);
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public void addNote(String note) {
        this.notes.add(note);
    }

    public void removeNote(String note) {
        this.notes.remove(note);
    }
}
