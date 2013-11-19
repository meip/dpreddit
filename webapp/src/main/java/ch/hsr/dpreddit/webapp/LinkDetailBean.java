package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.spa.Comment;
import ch.hsr.dpreddit.spa.Link;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class LinkDetailBean implements Serializable {
    private Link link;
    private String newCommentText;

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public String getNewCommentText() {
        return newCommentText;
    }

    public void setNewCommentText(String newCommentText) {
        this.newCommentText = newCommentText;
    }

    public void submitComment() {
        UserSessionBean userSessionBeanBean = (UserSessionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userSessionBean");
        Comment comment = new Comment(newCommentText, userSessionBeanBean.getUser(), link);
        link.addComment(comment);
        this.newCommentText = "";
    }
}
