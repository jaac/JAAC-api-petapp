package lpa.api.dtos;

import java.util.Arrays;

import javax.validation.constraints.NotNull;

import lpa.api.documents.core.Breed;
import lpa.api.documents.core.PetType;

public class PetTypeDto {

	private String id;
	@NotNull
	private String name;

	private Breed[] breed;

	public PetTypeDto() {
		// For Frame Work
	}

	public PetTypeDto(PetType petType) {
		this.id = petType.getId();
		this.name = petType.getName();
		this.breed = petType.getBreed();
	}

	public PetTypeDto(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Breed[] getBreed() {
		return breed;
	}

	public void setBreed(Breed[] breed) {
		this.breed = breed;
	}

	@Override
	public String toString() {
		return "PetTypeDto [id=" + id + ", name=" + name + ", breed=" + Arrays.toString(breed) + "]";
	}

}
