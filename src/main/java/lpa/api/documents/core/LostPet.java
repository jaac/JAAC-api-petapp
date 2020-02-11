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

    private boolean active;

    private boolean found;

    private Location location;

    private LostWay lostWay;

    private HealthCondition healthCondition;

    private boolean gratification;

    private String description;

    @DBRef
    private User user;

    @DBRef
    private Pet pet;

    @DateTimeFormat(iso = ISO.DATE)
    private Date dateLost;

    @DateTimeFormat(iso = ISO.DATE)
    private Date dateAdd;

    @DateTimeFormat(iso = ISO.DATE)
    private Date dateUpd;

    public LostPet() {
        this.setActive(true);
    }

    // Construct for pets lost by users
    public LostPet(boolean found, Location location, String description, HealthCondition healthCondition, Pet pet,
                   User user, LostWay lost_way, boolean gratification) {
        this();
        this.found = found;
        this.location = location;
        this.description = description;
        this.healthCondition = healthCondition;
        this.gratification = gratification;
        this.pet = pet;
        this.lostWay = lost_way;
        this.user = user;
    }

    public LostPet(boolean found, Location location, String description, HealthCondition healthCondition, Pet pet,
                   User user) {
        this(found, location, description, healthCondition, pet, user, null, false);

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

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Date getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(Date dateUpd) {
        this.dateUpd = dateUpd;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    private String dateFormat(Date dateAdd) {
        return dateAdd != null ? new SimpleDateFormat("dd-MMM-yyyy").format(this.dateAdd.getTime()) : "null";
    }

    public Date getDateLost() { return dateLost; }

    public void setDateLost(Date dateLost) { this.dateLost = dateLost; }

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
        return "LostPet{" +
                "id='" + id + '\'' +
                ", active=" + active +
                ", dateAdd=" + this.dateFormat(dateAdd) +
                ", dateUpd=" + this.dateFormat(dateAdd) +
                ", dateLost=" + this.dateFormat(dateLost) +
                ", found=" + found +
                ", location=" + location +
                ", lostWay=" + lostWay +
                ", healthCondition=" + healthCondition +
                ", gratification=" + gratification +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", pet=" + pet +
                '}';
    }

}
