package ch.hsr.dpreddit.webapp.validation;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import java.net.MalformedURLException;
import java.net.URL;

@FacesValidator("linkValidator")
public class LinkValidator implements Validator {
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        if (value == null || "".equals((String)value)) {
            FacesMessage msg = new FacesMessage("No link value!", "Link Validation Error");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
        try {
            URL url = new URL((String) value);
        } catch (MalformedURLException e) {
            FacesMessage msg = new FacesMessage("No valid link value!", "Link Validation Error");
            msg.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(msg);
        }
    }
}
