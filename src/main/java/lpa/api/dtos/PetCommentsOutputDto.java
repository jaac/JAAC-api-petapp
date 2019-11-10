package lpa.api.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lpa.api.documents.core.Image;
import lpa.api.documents.core.Comments;
import lpa.api.documents.core.Location;

public class PetCommentsOutputDto{

	@NotNull
	private String id;

	private boolean iSaw;

	private String userId;

	private String comment;

	private Date date;

	private Image petImage;

	private Location location;

	public PetCommentsOutputDto() {
		// Empty for framework
	}

	public PetCommentsOutputDto(Comments comments) {
		this.id = comments.getId();
		this.iSaw = comments.isiSaw();
		this.userId = comments.getUser().getId();
		this.comment = comments.getComment();
		this.date = comments.getDate();
		this.petImage = comments.getPetImage();
		this.location = comments.getLocation();
		
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String user) {
		this.userId = user;
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

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return "PetCommentsOutputDto [id=" + id + ", iSaw=" + iSaw + ", userId=" + userId + ", comment=" + comment
				+ ", date=" + date + ", petImage=" + petImage + ", location=" + location + "]";
	}

}
