package lpa.api.dtos;

import java.util.Date;

import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.Location;

public class LostPetOutputDto {
	private String id;

	private boolean active;

	private boolean found;

	private Location location;

	private String description;

	private String healthCondition;

	private PetOutPutDto pet;

	private String userId;

	private String lostWay;

	private boolean gratification;

	private Date dateAdd;

	private Date dateUpd;

	public LostPetOutputDto() {

	}

	public LostPetOutputDto(LostPet lostPet) {
		this.setId(lostPet.getId());
		this.active = lostPet.isActive();
		this.found = lostPet.isFound();
		this.location = lostPet.getLocation();
		this.description = lostPet.getDescription();
		this.healthCondition = lostPet.getHealthCondition().getName();
		this.pet = new PetOutPutDto(lostPet.getPet());
		this.userId = lostPet.getUser().getId();
		this.lostWay = lostPet.getLostWay().lostWayName();
		this.gratification = lostPet.isGratification();
		this.dateAdd = lostPet.getDateAdd();
		this.dateUpd = lostPet.getDateUpd();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getHealthCondition() {
		return healthCondition;
	}

	public void setHealthCondition(String healthCondition) {
		this.healthCondition = healthCondition;
	}

	public PetOutPutDto getPet() {
		return pet;
	}

	public void setPet(PetOutPutDto pet) {
		this.pet = pet;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLostWay() {
		return lostWay;
	}

	public void setLostWay(String lostWay) {
		this.lostWay = lostWay;
	}

	public boolean isGratification() {
		return gratification;
	}

	public void setGratification(boolean gratification) {
		this.gratification = gratification;
	}

	public Date getDateAdd() {
		return dateAdd;
	}

	public void setDateAdd(Date dateAdd) {
		this.dateAdd = dateAdd;
	}


	@Override
	public String toString() {
		return "LostPetOutputDto{" +
				"id='" + id + '\'' +
				", active=" + active +
				", found=" + found +
				", location=" + location +
				", description='" + description + '\'' +
				", healthCondition='" + healthCondition + '\'' +
				", pet=" + pet +
				", userId='" + userId + '\'' +
				", lostWay='" + lostWay + '\'' +
				", gratification=" + gratification +
				", registrationDate=" + dateAdd +
				'}';
	}
}
