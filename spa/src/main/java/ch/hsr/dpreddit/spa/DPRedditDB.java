package ch.hsr.dpreddit.spa;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public final class DPRedditDB {
    private Set<Link> linkSet = new HashSet<>();
    private Set<User> userSet = new HashSet<>();

    private static DPRedditDB instance;

    private DPRedditDB() {

        User user = new User("test", "test");
        addUser(user);

        Link link = new Link("Please do not visit this site during lectures!", "http://9gag.com", user);
        addPost(link);

        Comment comment = new Comment("What an awesome comment for that link", user, link);
        link.addComment(comment);
    }

    public synchronized static DPRedditDB getInstance() {
        if (instance == null) {
            instance = new DPRedditDB();
        }
        return instance;
    }

    public boolean addPost(Link link) {
        return this.linkSet.add(link);
    }

    public boolean addUser(User user) {
        return this.userSet.add(user);
    }

    public Set<Link> getLinkSet() {
        return linkSet;
    }

    public Link getPostById(long id) {
        for(Link link : DPRedditDB.getInstance().getLinkSet()) {
            if(link.getId() == id) return link;
        }
        return null;
    }

    public Set<User> getUserSet() {
        return userSet;
    }
}
