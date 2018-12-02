package io.github.raduorleanu.sep4.models;

import java.io.Serializable;
import java.util.List;
import java.util.Random;

public class Event implements Serializable {

    private User host;
    //private List<User> participants;
    private double rating;
    private String date;
    private String description;
    private String location;
    private List<String> requirements;
    private String _id;

    public Event(){}

    public Event(User host, String description) {
        this.host = host;
        this.description = description;
        _id = String.valueOf(new Random().nextLong());
    }

    public Event(User host, double rating, String date, String description, String location, List<String> requirements) {
        this.host = host;
        //this.participants = participants;
        this.rating = rating;
        this.date = date;
        this.description = description;
        this.location = location;
        this.requirements = requirements;
        _id = String.valueOf(new Random().nextLong());
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public User getHost() {
        return host;
    }

    public void setHost(User host) {
        this.host = host;
    }

//    public List<User> getParticipants() {
//        return participants;
//    }
//
//    public void setParticipants(List<User> participants) {
//        this.participants = participants;
//    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
//                ", participants=" + participants +
                ", rating=" + rating +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", requirements=" + requirements +
                '}';
    }
}
