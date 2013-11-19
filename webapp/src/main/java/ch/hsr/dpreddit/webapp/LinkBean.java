package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.spa.DPRedditDB;
import ch.hsr.dpreddit.spa.Link;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.util.Set;

@ManagedBean(name= "linkbean")
@ApplicationScoped
public class LinkBean {

    public Set<Link> getPosts() {
        return DPRedditDB.getInstance().getLinkSet();
    }
}
