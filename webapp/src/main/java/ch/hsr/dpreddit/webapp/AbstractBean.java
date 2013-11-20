package ch.hsr.dpreddit.webapp;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractBean {
    protected void addMessage(String i18nkey, FacesMessage.Severity severity) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.getCurrentInstance().addMessage(null, new FacesMessage(severity, fc.getApplication().evaluateExpressionGet(fc, "#{msg['"+i18nkey+"']}", String.class), null));
    }

}
