package io.github.raduorleanu.sep4.models;

import java.util.Date;
import java.util.List;

public class Event {

    private User host;
    private List<User> participants;
    private double rating;
    private Date date;
    private String description;
    private String location;
    private List<String> requirements;

    public Event(){}

    public Event(User host, String description) {
        this.host = host;
        this.description = description;
    }

    public Event(User host, List<User> participants, double rating, Date date, String description, String location, List<String> requirements) {
        this.host = host;
        this.participants = participants;
        this.rating = rating;
        this.date = date;
        this.description = description;
        this.location = location;
        this.requirements = requirements;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

    public List<User> getParticipants() {
        return participants;
    }

    public void setParticipants(List<User> participants) {
        this.participants = participants;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public List<String> getRequirements() {
        return requirements;
    }

    public void setRequirements(List<String> requirements) {
        this.requirements = requirements;
    }

    @Override
    public String toString() {
        return "Event{" +
                "host=" + host +
                ", participants=" + participants +
                ", rating=" + rating +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", requirements=" + requirements +
                '}';
    }
}
