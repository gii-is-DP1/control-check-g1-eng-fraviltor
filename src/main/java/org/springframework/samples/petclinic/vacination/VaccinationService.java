package org.springframework.samples.petclinic.vacination;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VaccinationService {
	
	@Autowired
	private VaccinationRepository vr;
	
    public List<Vaccination> getAll(){
        return vr.findAll();
    }

    public List<Vaccine> getAllVaccines(){
        return vr.findAllVaccines();
    }

    public Vaccine getVaccine(String typeName) {
        return vr.findVaccineByName(typeName);
    }

    @Transactional(rollbackFor = UnfeasibleVaccinationException.class)
    public Vaccination save(Vaccination p) throws UnfeasibleVaccinationException {
    	PetType petType = p.getVaccinatedPet().getType();
		if (petType!=p.getVaccine().getPetType()) {
			throw new UnfeasibleVaccinationException();
        }else
            vr.save(p);
		return p;
    }
    
}
