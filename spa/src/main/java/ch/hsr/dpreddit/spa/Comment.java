package ch.hsr.dpreddit.spa;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class Comment extends Rateable implements Comparable<Comment> {
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

        ResourceBundle bundle = ResourceBundle.getBundle("ch.hsr.dpreddit.messages");

        Integer value = components.get("years");
        if (value > 0) {
            return value + " " + (value > 1 ? bundle.getString("dpreddit.year_plural") : bundle.getString("dpreddit.year"));
        }

        value = components.get("months");
        if (value > 0) {
            return value + " " + (value > 1 ? bundle.getString("dpreddit.month_plural") : bundle.getString("dpreddit.month"));
        }

        value = components.get("weeks");
        if (value > 0) {
            return value + " " + (value > 1 ? bundle.getString("dpreddit.week_plural") : bundle.getString("dpreddit.week"));
        }

        value = components.get("days");
        if (value > 0) {
            return value + " " + (value > 1 ? bundle.getString("dpreddit.day_plural") : bundle.getString("dpreddit.day"));
        }

        value = components.get("hours");
        if (value > 0) {
            return value + " " + (value > 1 ? bundle.getString("dpreddit.hour_plural") : bundle.getString("dpreddit.hour"));
        }

        value = components.get("minutes");
        if (value > 0) {
            return value + " " + (value > 1 ? bundle.getString("dpreddit.minute_plural") : bundle.getString("dpreddit.minute"));
        }

        value = components.get("seconds");
        if (value > 0) {
            return value + " " + (value > 1 ? bundle.getString("dpreddit.second_plural") : bundle.getString("dpreddit.second"));
        }

        return "0 " + bundle.getString("dpreddit.second_plural");
    }

    @Override
    public int compareTo(Comment o) {
        if(getVotes() > o.getVotes()) {
            return 1;
        }
        if(getVotes() < o.getVotes()) {
            return -1;
        }
        if(getDate().getTime() > o.getDate().getTime()) {
            return 1;
        }
        if(getDate().getTime() < o.getDate().getTime()) {
            return -1;
        }
        return getCommentText().compareTo(o.getCommentText());
    }
}
