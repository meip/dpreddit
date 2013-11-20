package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.spa.DPRedditDB;
import ch.hsr.dpreddit.spa.User;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean(name = "registrationBean")
public class RegistrationBean extends AbstractBean {
    String username;
    String password;
    String confirmPassword;

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String addUser() {
        if (DPRedditDB.getInstance().addUser(new User(username, password))) {
            addMessage("dpreddit.registrationsuccess",FacesMessage.SEVERITY_INFO);
        } else {
            addMessage("dpreddit.registrationfailed",FacesMessage.SEVERITY_ERROR);
        }
        return "index";
    }
}