package lpa.api.dtos;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lpa.api.documents.core.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class LostPetInputDto {

    @DateTimeFormat(iso = ISO.DATE)
    private Date dateLost;

    private boolean found;

    @NotNull
    private boolean active;

    @NotNull
    private Location location;

    private LostWay lostWay;

    private HealthCondition healthCondition;

    private boolean gratification;

    private String description;

    @NotNull
    private String userId;

    @NotNull
    private PetInputDto pet;

    public LostPetInputDto() {
        this.active = true;
    }

    public LostPetInputDto(boolean found, Location location, String description, HealthCondition healthCondition,
                           PetInputDto petInputDto, String userId, LostWay lostWay, boolean gratification, Date dateLost) {
        this();
        this.found = found;
        this.location = location;
        this.description = description;
        this.healthCondition = healthCondition;
        this.pet = petInputDto;
        this.userId = userId;
        this.lostWay = lostWay;
        this.gratification = gratification;
        this.dateLost = dateLost;
    }

    public LostPetInputDto(boolean found, Location location, String description, HealthCondition healthCondition,
                           PetInputDto pet, String userId) {
        this(found, location, description, healthCondition, pet, userId, null, false,null);
    }

    public LostPetInputDto(LostPet lostPet) {
        this.active = lostPet.isActive();
        this.found = lostPet.isFound();
        this.location = lostPet.getLocation();
        this.description = lostPet.getDescription();
        this.healthCondition = lostPet.getHealthCondition();
        this.pet = new PetInputDto(lostPet.getPet());
        this.userId = lostPet.getUser().getId();
        this.lostWay = lostPet.getLostWay();
        this.gratification = lostPet.isGratification();
        this.dateLost = lostPet.getDateAdd();
    }

    public Date getDateLost() {
        return dateLost;
    }

    public void setDateLost(Date dateLost) {
        this.dateLost = dateLost;
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


	@Override
	public String toString() {
		return "LostPetInputDto{" +
				"dateLost=" + dateLost +
				", found=" + found +
				", active=" + active +
				", location=" + location +
				", lostWay=" + lostWay +
				", healthCondition=" + healthCondition +
				", gratification=" + gratification +
				", description='" + description + '\'' +
				", userId='" + userId + '\'' +
				", pet=" + pet +
				'}';
	}
}
