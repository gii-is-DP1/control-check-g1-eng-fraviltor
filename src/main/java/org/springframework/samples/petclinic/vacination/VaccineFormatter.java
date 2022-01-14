package org.springframework.samples.petclinic.vacination;

import java.text.ParseException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

@Component
public class VaccineFormatter implements Formatter<Vaccine>{

	@Autowired
	private VaccinationService vs;
	
    @Override
    public String print(Vaccine object, Locale locale) {
    	String name = object.getName();
        return name;
    }

    @Override
    public Vaccine parse(String text, Locale locale) throws ParseException {
    	Vaccine p = this.vs.getVaccine(text);
        if(p==null) {
        	throw new ParseException(text,0);
        } else {
        	return p;
        }
    }
    
}
