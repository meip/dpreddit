package ch.hsr.dpreddit.spa;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Comment extends Rateable {
    private static long IDINCREMENTER = 1;
    private long id;
    private User author;
    private Link link;
    private String commentText;
    private Date date;

    public Comment(String commentText, User author, Link link) {
        this.id = IDINCREMENTER++;
        this.commentText = commentText;
        this.author = author;
        this.link = link;
        this.date = new Date();
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public Map<String, Integer> getDateComponents() {
        Date now = new Date();

        Map<String, Integer> components = new HashMap<String, Integer>();

        int seconds = (int) (now.getTime() - getDate().getTime()) / 1000;
        int minutes = (int) seconds / 60;
        int hours = (int) seconds / 3600;
        int days = (int) seconds / (3600 * 24);
        int weeks = (int) days / 7;
        int months = (int) days / 30;
        int years = (int) days / 365;

        components.put("seconds", new Integer(seconds));
        components.put("minutes", new Integer(minutes));
        components.put("hours", new Integer(hours));
        components.put("days", new Integer(days));
        components.put("weeks", new Integer(weeks));
        components.put("months", new Integer(months));
        components.put("years", new Integer(years));
        return components;
    }

    public String getAgo() {
        Map<String, Integer> components = getDateComponents();

        Integer value = components.get("years");
        if (value > 0) {
            return value + " year" + (value > 1 ? "s" : "");
        }

        value = components.get("months");
        if (value > 0) {
            return value + " month" + (value > 1 ? "s" : "");
        }

        value = components.get("weeks");
        if (value > 0) {
            return value + " week" + (value > 1 ? "s" : "");
        }

        value = components.get("days");
        if (value > 0) {
            return value + " day" + (value > 1 ? "s" : "");
        }

        value = components.get("hours");
        if (value > 0) {
            return value + " hour" + (value > 1 ? "s" : "");
        }

        value = components.get("minutes");
        if (value > 0) {
            return value + " minute" + (value > 1 ? "s" : "");
        }

        value = components.get("seconds");
        if (value > 0) {
            return value + " second" + (value > 1 ? "s" : "");
        }

        return "now";
    }
}
