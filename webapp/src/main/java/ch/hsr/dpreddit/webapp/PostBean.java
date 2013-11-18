package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.spa.DPRedditDB;
import ch.hsr.dpreddit.spa.Post;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Set;

@ManagedBean(name="postbean")
@ApplicationScoped
public class PostBean {

    public Set<Post> getPosts() {
        return DPRedditDB.getInstance().getPostSet();
    }
}
