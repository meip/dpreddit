package ch.hsr.dpreddit.spa;

import java.util.*;

public class Post {
    String title;
    String linkURL;
    User postAuthor;
    Post parentPost;
    Date date;
    Set<User> positiveVotes = new HashSet<>();
    Set<User> negativeVotes = new HashSet<>();

    public Post(String title, String linkURL, User user) {
        this.title = title;
        this.linkURL = linkURL;
        this.postAuthor = user;
        this.date = new Date();
    }

    public Post(String title, String linkURL, User user, Post parentPost) {
        this(title, linkURL, user);
        this.parentPost = parentPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLinkURL() {
        return linkURL;
    }

    public void setLinkURL(String linkURL) {
        this.linkURL = linkURL;
    }

    public User getPostAuthor() {
        return postAuthor;
    }

    public void setPostAuthor(User postAuthor) {
        this.postAuthor = postAuthor;
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

    public int getVotes() {
        return positiveVotes.size() - negativeVotes.size();
    }

    public int vote(User user) {
        if (negativeVotes.contains(user)) {
            negativeVotes.remove(user);
        }
        positiveVotes.add(user);
        return getVotes();
    }

    public int devote(User user) {
        if (positiveVotes.contains(user)) {
            positiveVotes.remove(user);
        }
        negativeVotes.add(user);
        return getVotes();
    }
}
