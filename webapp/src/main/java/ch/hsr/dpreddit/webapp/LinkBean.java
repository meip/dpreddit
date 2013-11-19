package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.spa.DPRedditDB;
import ch.hsr.dpreddit.spa.Link;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.*;

@ManagedBean(name= "linkbean")
@ApplicationScoped
public class LinkBean {

    public Collection<Link> getPosts() {

        List<Link> list = new LinkedList<>(DPRedditDB.getInstance().getLinkSet());
        Collections.sort(list, new Comparator<Link>() {
            @Override
            public int compare(Link o1, Link o2) {
                return o2.compareTo(o1);
            }
        });

        return list;
    }
}
