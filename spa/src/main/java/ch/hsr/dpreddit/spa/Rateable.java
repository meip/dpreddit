package ch.hsr.dpreddit.spa;

import java.util.HashSet;
import java.util.Set;

public class Rateable {

    private Set<User> positiveVotes = new HashSet<>();
    private Set<User> negativeVotes = new HashSet<>();


    public int getVotes() {
        return positiveVotes.size() - negativeVotes.size();
    }

    public void vote(User user) {
        if (negativeVotes.contains(user)) {
            negativeVotes.remove(user);
        }
        positiveVotes.add(user);
    }

    public void devote(User user) {
        if (positiveVotes.contains(user)) {
            positiveVotes.remove(user);
        }
        negativeVotes.add(user);
    }
}
