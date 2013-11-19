package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.spa.DPRedditDB;
import ch.hsr.dpreddit.spa.Link;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "newLinkBean")
public class NewLinkBean extends AbstractBean {
    String linkTitle;
    String linkUrl;

    public String getLinkTitle() {
        return linkTitle;
    }

    public void setLinkTitle(String linkTitle) {
        this.linkTitle = linkTitle;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String submitLink() {
        UserSessionBean userSessionBeanBean = (UserSessionBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("userSessionBean");
        Link newLink = new Link(linkTitle, linkUrl, userSessionBeanBean.getUser());
        if (DPRedditDB.getInstance().addPost(newLink)) {
            addMessage("Link successfully submitted", FacesMessage.SEVERITY_INFO);
        } else {
            addMessage("Problem while submitting link", FacesMessage.SEVERITY_ERROR);
        }
        return "index";
    }
}