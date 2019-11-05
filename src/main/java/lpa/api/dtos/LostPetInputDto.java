package lpa.api.dtos;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lpa.api.documents.core.PetComments;
import lpa.api.documents.core.Location;
import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.Report;


public class LostPetInputDto {

	@DateTimeFormat(iso = ISO.DATE)

	private Date registrationDate;

	private boolean found;
	
	@NotNull
	private boolean active;
	
	@NotNull
	private Location location;

	private String lostWay;

	private String healthConditionId;

	private boolean gratification;

	private String description;

	private Report[] reportList;

	@NotNull
	private String userId;

	@NotNull
	private PetInputDto pet;

	private PetComments[] petComments;

	public LostPetInputDto() {
		this.active = true;
	}

	public LostPetInputDto(boolean found, Location location, String description, String healthConditionId,
			PetInputDto petInputDto, String userId, String lost_way, boolean gratification, Report[] report) {
		this();
		this.found = found;
		this.location = location;
		this.description = description;
		this.healthConditionId = healthConditionId;
		this.pet = petInputDto;
		this.userId = userId;
		this.lostWay = lost_way;
		this.gratification = gratification;
		this.reportList = report;

	}

	public LostPetInputDto(boolean found, Location location, String description, String healthConditionId,
			PetInputDto pet, String userId) {
		this(found, location, description, healthConditionId, pet, userId, null, false, null);
	}

	public LostPetInputDto(LostPet lostPet) {
		this.active = lostPet.isActive();
		this.found = lostPet.isFound();
		this.location = lostPet.getLocation();
		this.description = lostPet.getDescription();
		this.healthConditionId = lostPet.getHealthCondition().getId();
		this.pet = new PetInputDto(lostPet.getPet());
		this.userId = lostPet.getUser().getId();
		this.lostWay = lostPet.getLostWay().getId();
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

	public Location getPetLocation() {
		return location;
	}

	public void setPetLocation(Location location) {
		this.location = location;
	}

	public String getLostWay() {
		return lostWay;
	}

	public void setLostWay(String lostWay) {
		this.lostWay = lostWay;
	}

	public String getHealthConditionId() {
		return healthConditionId;
	}

	public void setHealthCondition(String healthConditionId) {
		this.healthConditionId = healthConditionId;
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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public PetInputDto getPet() {
		return pet;
	}

	public void setPet(PetInputDto petInputDto) {
		this.pet = petInputDto;
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
		return "LostPetInputDto[ active=" + this.active + ", found=" + this.found + ", date=" + date + ", location="
				+ this.location + ", description=" + this.description + ", lostWay=" + this.lostWay + ", pet="
				+ this.pet + ", healthConditionId=" + this.healthConditionId + ", gratification=" + this.gratification
				+ ", reportList=" + this.reportList + "]";
	}

}
