package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.spa.DPRedditDB;
import ch.hsr.dpreddit.spa.Post;
import ch.hsr.dpreddit.spa.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import java.util.Set;

@ManagedBean(name="postbean")
public class PostBean {

    public Set<Post> getPosts() {
        return DPRedditDB.getInstance().getPostSet();
    }

    public String votePost(Post post) {
        UserSessionBean userSessionBeanBean = (UserSessionBean)FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userSessionBean");

        if(userSessionBeanBean.isLoggedIn()) {
            System.out.println( post.vote(userSessionBeanBean.getUser()));
        }

        FacesContext.getCurrentInstance().renderResponse();
        return "/index";
    }
}
