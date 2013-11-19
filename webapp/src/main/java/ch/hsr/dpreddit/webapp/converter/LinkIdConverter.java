package ch.hsr.dpreddit.webapp.converter;

import ch.hsr.dpreddit.spa.DPRedditDB;
import ch.hsr.dpreddit.spa.Link;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class LinkIdConverter implements Converter {
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        if (value == null) {
            return null;
        }
        return DPRedditDB.getInstance().getPostById(Long.valueOf(value));
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        String string = null;
        if (value instanceof Link) string = Long.valueOf(((Link) value).getId()).toString();
        return string;
    }
}
