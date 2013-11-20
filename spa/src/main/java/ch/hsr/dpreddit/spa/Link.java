package ch.hsr.dpreddit.spa;

import java.util.*;

public class Link extends Rateable implements  Comparable<Link>{
    private static long IDINCREMENTER = 1;
    private long id;
    private String title;
    private String linkURL;
    private User postAuthor;
    private Date date;
    private Set<Comment> comments = new HashSet<>();

    public Link(String title, String linkURL, User user) {
        this.id = IDINCREMENTER++;
        this.title = title;
        this.linkURL = linkURL;
        this.postAuthor = user;
        this.date = new Date();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean addComment(Comment comment) {
        return comments.add(comment);
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public Collection<Comment> getSortedComments() {
        List<Comment> list = new LinkedList<>(comments);
        Collections.sort(list, new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o2.compareTo(o1);
            }
        });

        return list;
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
    public int compareTo(Link o) {
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
        return getTitle().compareTo(o.getTitle());
    }
}
