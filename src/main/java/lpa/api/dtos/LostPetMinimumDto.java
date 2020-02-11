package lpa.api.dtos;

import java.util.Date;

import lpa.api.documents.core.LostPet;

public class LostPetMinimumDto {

	private String id;
	private String name;
	private String type;
	private boolean found;
	private Date date;
	private String image;

	public LostPetMinimumDto() {

	}

	public LostPetMinimumDto(LostPet lostPet) {
		this.id = lostPet.getId();
		this.name = lostPet.getPet().getName();
		this.type = lostPet.getPet().getSpecies().getName();
		this.found = lostPet.isFound();
		this.date = lostPet.getDateAdd();
		//this.image = lostPet.getPet().getPetImages()[0].getImage();
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "LostPetMinimumDto [id=" + id + ", name=" + name + ", type=" + type + ", found=" + found + ", date="
				+ date + ", image=" + image + "]";
	}
}
