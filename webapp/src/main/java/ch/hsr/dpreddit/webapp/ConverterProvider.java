package ch.hsr.dpreddit.webapp;

import ch.hsr.dpreddit.webapp.converter.LinkIdConverter;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.convert.Converter;

@ManagedBean
@ApplicationScoped
public class ConverterProvider {
    public Converter getLinkIdConverter() {
        return new LinkIdConverter();
    }
}
