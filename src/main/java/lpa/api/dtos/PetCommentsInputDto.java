package lpa.api.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lpa.api.documents.core.Image;

import lpa.api.documents.core.Location;

public class PetCommentsInputDto {

	@NotNull
	private String userId;

	private boolean iSaw;

	private String comment;

	@NotNull
	private Date date;

	private Image petImage;

	@NotNull
	private String lostPetId;

	private Location location;

	public PetCommentsInputDto() {

	}

	public PetCommentsInputDto(String userId, boolean iSaw, String comment, Date date, Image petImage, String lostPetId,
			Location location) {
		this.userId = userId;
		this.iSaw = iSaw;
		this.comment = comment;
		this.date = date;
		this.petImage = petImage;
		this.lostPetId = lostPetId;
		this.location = location;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public boolean isiSaw() {
		return iSaw;
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

	public String getLostPetId() {
		return lostPetId;
	}

	public void setLostPetId(String lostPetId) {
		this.lostPetId = lostPetId;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "PetCommentsInputDto [userId=" + userId + ", iSaw=" + iSaw + ", comment=" + comment + ", date=" + date
				+ ", petImage=" + petImage + ", lostPetId=" + lostPetId + ", location=" + location + "]";
	}

}
