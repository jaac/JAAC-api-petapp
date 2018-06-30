package lpa.api.dtos;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lpa.api.documents.core.HealthCondition;
import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.LostWay;
import lpa.api.documents.core.Pet;
import lpa.api.documents.core.PetComments;
import lpa.api.documents.core.PetLocation;
import lpa.api.documents.core.Report;
import lpa.api.documents.core.User;

public class LostPetInputDto {

	@NotNull
	private String id;

	@DateTimeFormat(iso = ISO.DATE)
	private Date registrationDate;

	private boolean found;

	private boolean active;

	private PetLocation petLocation;

	private LostWay lostWay;

	private HealthCondition healthCondition;

	private boolean gratification;

	private String description;

	private Report[] reportList;

	private User user;

	private Pet pet;

	private PetComments[] petComments;

	public LostPetInputDto() {
		// Empty for framework
	}

	public LostPetInputDto(boolean found, PetLocation petLocation, String description, HealthCondition healthCondition,
			Pet pet, User user, LostWay lost_way, boolean gratification, Report[] report) {
		this.found = found;
		this.petLocation = petLocation;
		this.description = description;
		this.healthCondition = healthCondition;
		this.pet = pet;
		this.user = user;
		this.lostWay = lost_way;
		this.gratification = gratification;
		this.reportList = report;

	}

	public LostPetInputDto(boolean found, PetLocation petLocation, String description, HealthCondition healthCondition,
			Pet pet, User user) {
		this(found, petLocation, description, healthCondition, pet, user, null, false, null);
	}

	public LostPetInputDto(LostPet lostPet) {
		this.active = lostPet.isActive();
		this.found = lostPet.isFound();
		this.petLocation = lostPet.getPetLocation();
		this.description = lostPet.getDescription();
		this.healthCondition = lostPet.getHealthCondition();
		this.pet = lostPet.getPet();
		this.user = lostPet.getUser();
		this.lostWay = lostPet.getLostWay();
		this.gratification = lostPet.isGratification();
		this.reportList = lostPet.getPetReportList();
		this.registrationDate = lostPet.getRegistrationDate();
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
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

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public PetLocation getPetLocation() {
		return petLocation;
	}

	public void setPetLocation(PetLocation petLocation) {
		this.petLocation = petLocation;
	}

	public LostWay getLostWay() {
		return lostWay;
	}

	public void setLostWay(LostWay lostWay) {
		this.lostWay = lostWay;
	}

	public HealthCondition getHealthCondition() {
		return healthCondition;
	}

	public void setHealthCondition(HealthCondition healthCondition) {
		this.healthCondition = healthCondition;
	}

	public boolean isGratification() {
		return gratification;
	}

	public void setGratification(boolean gratification) {
		this.gratification = gratification;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Report[] getReportList() {
		return reportList;
	}

	public void setReportList(Report[] reportList) {
		this.reportList = reportList;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Pet getPet() {
		return pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public PetComments[] getPetComments() {
		return petComments;
	}

	public void setPetComments(PetComments[] petComments) {
		this.petComments = petComments;
	}

	@Override
	public String toString() {
		String date = "null";
		if (registrationDate != null) {
			date = new SimpleDateFormat("dd-MMM-yyyy").format(registrationDate.getTime());
		}
		return "LostPetInputDto[ " + this.id + " ,active=" + this.active + ", found=" + this.found + ", date=" + date
				+ ", petLocation=" + this.petLocation + ", description=" + this.description + ", lostWay="
				+ this.lostWay + ", pet=" + this.pet + ", healthCondition=" + this.healthCondition + ", gratification="
				+ this.gratification + ", reportList=" + this.reportList + "]";
	}

}
