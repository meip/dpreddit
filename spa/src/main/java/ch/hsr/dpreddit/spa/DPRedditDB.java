package ch.hsr.dpreddit.spa;

import java.util.HashSet;
import java.util.Set;

public final class DPRedditDB {
    private Set<Post> postSet = new HashSet<>();
    private Set<User> userSet = new HashSet<>();

    private static DPRedditDB instance;

    private DPRedditDB() {
    }

    public synchronized static DPRedditDB getInstance() {
        if (instance == null) {
            instance = new DPRedditDB();
        }
        return instance;
    }

    public boolean addPost(Post post) {
        return this.postSet.add(post);
    }

    public boolean addUser(User user) {
        return this.userSet.add(user);
    }

    public Set<Post> getPostSet() {
        return postSet;
    }

    public Set<User> getUserSet() {
        return userSet;
    }
}
