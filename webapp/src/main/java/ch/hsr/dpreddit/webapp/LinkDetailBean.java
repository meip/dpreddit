package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.spa.Comment;
import ch.hsr.dpreddit.spa.Post;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean
@ViewScoped
public class LinkDetailBean implements Serializable {
    private Post post;
    private String newCommentText;

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getNewCommentText() {
        return newCommentText;
    }

    public void setNewCommentText(String newCommentText) {
        this.newCommentText = newCommentText;
    }

    public void submitComment() {
        UserSessionBean userSessionBeanBean = (UserSessionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userSessionBean");
        Comment comment = new Comment(newCommentText, userSessionBeanBean.getUser(), post);
        post.addComment(comment);
        this.newCommentText = "";
    }
}
