package lpa.api.dtos;

import javax.validation.constraints.NotNull;

public class BreedDto {

	@NotNull
	String name;

	public BreedDto() {
		// For Framework
	}

	public BreedDto(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "BreedDto [name=" + name + "]";
	}

}
