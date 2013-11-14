package ch.hsr.dpreddit.spa;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Post {
    String title;
    String text;
    User postAuthor;
    Post parentPost;
    Date date;
    Set<User> positiveVotes = new HashSet<>();
    Set<User> negativeVotes = new HashSet<>();

    public Post(String title, String text, User user) {
        this.title = title;
        this.text = text;
        this.postAuthor = user;
        this.date = new Date();
    }

    public Post(String title, String text, User user, Post parentPost) {
        this(title, text, user);
        this.parentPost = parentPost;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public int getVotes() {
        return positiveVotes.size() - negativeVotes.size();
    }

    public double getPreciseVotes() {
        int pos = positiveVotes.size();
        int n = pos + negativeVotes.size();

        double z = 1.96;
        double phat = 1 * pos / n;
        return (phat + z*z/(2*n) - z * Math.sqrt((phat*(1-phat)+z*z/(4*n))/n))/(1+z*z/n);
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
