package ch.hsr.dpreddit.webapp;

import javax.faces.bean.ManagedBean;

@ManagedBean(name = "editor")
public class EditorBean {

    private String value = "This is provided by JSF";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}