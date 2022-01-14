package org.springframework.samples.petclinic.vacination;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class VaccinationController {
	
	@Autowired
	private VaccinationService vs;
	private PetService ps;
	
	@GetMapping(path = "/vaccination/create")
	public String initCreationForm(ModelMap modelMap) {
		Vaccination v = new Vaccination();
		modelMap.addAttribute("vaccination", v);
		modelMap.addAttribute("vaccines", vs.getAllVaccines());
		modelMap.addAttribute("pets", ps.findAllPets());
		return "vaccination/createOrUpdateVaccinationForm";
	}
	
	@PostMapping(value = "/vaccination/create")
	public String processCreationForm(@Valid Vaccination v, BindingResult result, ModelMap modelMap) {
		if (result.hasErrors()) {
			modelMap.addAttribute("vaccines", vs.getAllVaccines());
			modelMap.addAttribute("pets", ps.findAllPets());
			return "vaccination/createOrUpdateVaccinationForm";
		}
		else {
			try {
				vs.save(v);
			} catch (UnfeasibleVaccinationException e) {
				modelMap.addAttribute("message", "La mascota seleccionada no puede recibir la vacuna especificada");
				return "vaccination/createOrUpdateVaccinationForm";
			}
			modelMap.addAttribute("message", "Se ha guardado correctamente");
			return "welcome";
		}
	}
}
