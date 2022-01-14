package org.springframework.samples.petclinic.vacination;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.PetType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Vaccine extends BaseEntity {

	@NotNull
	@Size(min=3, max=50)
	@Column(name="name", unique=true)
	String name;
	
	@Min(0)
	Double price;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "pet_type_id")
	private PetType petType;

	//Lombok no me funciona bien, por lo que genero manualmente los Getters y los Setters
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
		this.petType = petType;
	}
}
