package lpa.api.documents.core;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PetComments {

	@Id
	private String id;
	private boolean active;
	private boolean banned;

	@DBRef
	private User user;

	private boolean iSaw;
	private String comment;
	private Date date;
	private Image petImage;

	@DBRef
	private LostPet lostPet;

	private Report[] reportList;

	private PetLocation petLocation;

	public PetComments() {
		this.active = true;
		this.banned = false;
		this.date = new Date();
	}

	public PetComments(User user, boolean iSaw, LostPet lostPet, String comment, PetLocation petLocation,
			Image petImage) {
		this();
		this.user = user;
		this.user.setPassword("");
		this.iSaw = iSaw;
		this.comment = comment;
		this.setPetLocation(petLocation);
		this.petImage = petImage;
		this.lostPet = lostPet;
	}

	// Only Comment
	public PetComments(String comment, LostPet lostPet, User user) {
		this(user, false, lostPet, comment, null, null);
	}

	// With no Image
	public PetComments(User user, boolean iSaw, LostPet lostPet, String comment, PetLocation petLocation) {
		this(user, iSaw, lostPet, comment, petLocation, null);
	}

	public String getId() {
		return id;
	}

	public boolean isiSaw() {
		return iSaw;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public void setiSaw(boolean iSaw) {
		this.iSaw = iSaw;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Image getPetImage() {
		return petImage;
	}

	public void setPetImage(Image petImage) {
		this.petImage = petImage;
	}

	public Report[] getReportList() {
		return reportList;
	}

	public void setReportList(Report[] repotList) {
		this.reportList = repotList;
	}

	public PetLocation getPetLocation() {
		return petLocation;
	}

	public void setPetLocation(PetLocation petLocation) {
		this.petLocation = petLocation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LostPet getLostPet() {
		return lostPet;
	}

	public void setLostPet(LostPet lostPet) {
		this.lostPet = lostPet;
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
		return (id.equals(((PetComments) obj).id));
	}

	@Override
	public String toString() {
		return "PetComment[ id=" + this.id + ", date=" + this.date + ", iSaw=" + this.iSaw + ", lostPet=" + this.lostPet
				+ ", comment=" + this.comment + ", petLocation=" + this.petLocation + ", petImage=" + this.petImage
				+ ", reportList=" + this.reportList + "]";
	}

}
