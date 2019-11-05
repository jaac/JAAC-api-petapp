package lpa.api.dtos;

import java.util.Arrays;
import java.util.Date;

import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.Location;
import lpa.api.documents.core.Report;

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
	private Report[] reportList;
	private Date registrationDate;

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
		this.lostWay = lostPet.getLostWay().getName();
		this.gratification = lostPet.isGratification();
		this.reportList = lostPet.getPetReportList();
		this.registrationDate = lostPet.getRegistrationDate();
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

	public Report[] getReportList() {
		return reportList;
	}

	public void setReportList(Report[] reportList) {
		this.reportList = reportList;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "LostPetOutputDto [active=" + active + ", found=" + found + ", location=" + location + ", description="
				+ description + ", healthCondition=" + healthCondition + ", pet=" + pet + ", userId=" + userId
				+ ", lostWay=" + lostWay + ", gratification=" + gratification + ", reportList="
				+ Arrays.toString(reportList) + ", registrationDate=" + registrationDate + "]";
	}

}
