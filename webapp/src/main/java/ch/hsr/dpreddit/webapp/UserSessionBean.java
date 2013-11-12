package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.spa.DPRedditDB;
import ch.hsr.dpreddit.spa.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;

@ManagedBean(name = "userSessionBean")
@SessionScoped
public class UserSessionBean extends AbstractBean implements Serializable {
    private String username;
    private String password;
    private User user;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public String login() {
        FacesContext fc = FacesContext.getCurrentInstance();
        for (User user : DPRedditDB.getInstance().getUserSet()) {
            if (user.getUsername().equals(username) && password.equals(user.getPassword())) {
                this.user = user;
                System.err.println("Login success: " + user);
                addMessage("User Login Successful!!!", FacesMessage.SEVERITY_INFO);
                return "index";
            }
        }
        addMessage("User Login failed!!!",FacesMessage.SEVERITY_ERROR);
        return "loginfailure";
    }


    public void logout() {
        user = null;
    }

    public boolean isLoggedIn() {
        return (user != null);
    }

}
