package lpa.api.dtos;

import java.util.Date;

import lpa.api.documents.core.Pet;
import lpa.api.documents.core.PetLocation;

public class LostPetMinimumDto {

	private Pet pet;

	private String id;

	private boolean found;

	private PetLocation petLocation;

	private Date registrationDate;

	/**
	 * @return the registrationDate
	 */
	public Date getDate() {
		return registrationDate;
	}

	/**
	 * @param registrationDate
	 *            the registrationDate to set
	 */
	public void setDate(Date date) {
		this.registrationDate = date;
	}

	/**
	 * @return the petLocation
	 */
	public PetLocation getPetLocation() {
		return petLocation;
	}

	/**
	 * @param petLocation
	 *            the petLocation to set
	 */
	public void setPetLocation(PetLocation petLocation) {
		this.petLocation = petLocation;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	@Override
	public String toString() {
		return "LostPetMinimum [id=" + id + ", found =" + this.found + ", name=" + this.pet.getName() + ", city="
				+ this.petLocation.getCity() + ", imgSmall=" + this.pet.getPetImages()[0].getSmall() + ", registrationDate="
				+ this.registrationDate + "]";
	}
}
