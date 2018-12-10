package io.github.raduorleanu.sep4.models;

import java.io.Serializable;
import java.util.Objects;
import java.util.Random;

public class Comment implements Serializable {
    private User user;
    private String message;
    private String eventId;
    private String commentId;
    private String userId;

    public Comment(String userId, String message) {
        this.userId = userId;
        this.message = message;
    }

    public Comment(User user, String message, String eventId, String commentId) {
        this.user = user;
        this.message = message;
        this.eventId = eventId;
        this.commentId = commentId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "user=" + user +
                ", message='" + message + '\'' +
                ", eventId='" + eventId + '\'' +
                ", commentId='" + commentId + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return Objects.equals(user, comment.user) &&
                Objects.equals(message, comment.message) &&
                Objects.equals(eventId, comment.eventId) &&
                Objects.equals(commentId, comment.commentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, message, eventId, commentId);
    }
}
