package lpa.api.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;

import lpa.api.documents.core.Image;
import lpa.api.documents.core.PetComments;
import lpa.api.documents.core.PetLocation;

public class PetCommentsOutputDto {

	@NotNull
	private String id;

	private boolean iSaw;

	private String userId;

	private String comment;

	private Date date;

	private Image petImage;

	private PetLocation petLocation;

	public PetCommentsOutputDto() {

	}

	public PetCommentsOutputDto(PetComments petComments) {
		this.id = petComments.getId();
		this.iSaw = petComments.isiSaw();
		this.userId = petComments.getUser().getId();
		this.comment = petComments.getComment();
		this.date = petComments.getDate();
		this.petImage = petComments.getPetImage();
		this.petLocation = petComments.getPetLocation();
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

	public PetLocation getPetLocation() {
		return petLocation;
	}

	public void setPetLocation(PetLocation petLocation) {
		this.petLocation = petLocation;
	}

}
