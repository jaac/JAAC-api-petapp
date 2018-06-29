package lpa.api.documents.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@Document
public class LostPet {

	@Id
	private String id;

	@DateTimeFormat(iso = ISO.DATE)
	private Date registrationDate;

	private boolean found;

	private boolean active;

	private PetLocation petLocation;

	@DBRef
	private LostWay lostWay;

	@DBRef
	private HealthCondition healthCondition;

	private boolean gratification;

	private String description;

	private Report[] report;

	@DBRef
	private User user;

	private Pet pet;

	public LostPet() {
		this.registrationDate = new Date();
		this.setActive(true);
	}

	// Construct for pets lost by users
	public LostPet(boolean found, PetLocation petLocation, String description, HealthCondition healthCondition, Pet pet,
			User user, LostWay lost_way, boolean gratification) {
		this();
		this.found = found;
		this.petLocation = petLocation;
		this.description = description;
		this.healthCondition = healthCondition;
		this.gratification = gratification;
		this.pet = pet;
		this.lostWay = lost_way;
		this.user = user;
	}

	public LostPet(boolean found, PetLocation petLocation, String description, HealthCondition healthCondition, Pet pet,
			User user) {
		this(found, petLocation, description, healthCondition, pet, user, null, false);

	}

	public String getId() {
		return this.id;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}

	public LostWay getLostWay() {
		return lostWay;
	}

	public void setLostWay(LostWay lost_way) {
		this.lostWay = lost_way;
	}

	public boolean isGratification() {
		return gratification;
	}

	public Pet getPet() {
		return this.pet;
	}

	public void setPet(Pet pet) {
		this.pet = pet;
	}

	public void setGratification(boolean gratification) {
		this.gratification = gratification;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public HealthCondition getHealthCondition() {
		return healthCondition;
	}

	public void setHealthCondition(HealthCondition healthCondition) {
		this.healthCondition = healthCondition;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Report[] getPetReportList() {
		return report;
	}

	public void setPetReport(Report[] petReport) {
		this.report = petReport;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public int hashCode() {
		return this.id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		return (id.equals(((LostPet) obj).id));
	}

	@Override
	public String toString() {
		String date = "null";
		if (registrationDate != null) {
			date = new SimpleDateFormat("dd-MMM-yyyy").format(registrationDate.getTime());
		}
		return "LostPet[ " + this.id + " ,active=" + this.active + ", found=" + this.found + ", date=" + date
				+ ", petLocation=" + this.petLocation + ", description=" + this.description + ", lostWay="
				+ this.lostWay + ", pet=" + this.pet + ", healthCondition=" + this.healthCondition + ", gratification="
				+ this.gratification + ", report=" + this.report + "]";
	}

	public PetLocation getPetLocation() {
		return petLocation;
	}

	public void setPetLocation(PetLocation petLocation) {
		this.petLocation = petLocation;
	}

}
