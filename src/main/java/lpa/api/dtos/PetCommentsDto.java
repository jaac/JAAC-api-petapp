package lpa.api.dtos;

import java.util.Arrays;
import java.util.Date;

import javax.validation.constraints.NotNull;

import lpa.api.documents.core.Image;
import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.PetComments;
import lpa.api.documents.core.PetLocation;
import lpa.api.documents.core.Report;
import lpa.api.documents.core.User;

public class PetCommentsDto {

	@NotNull
	private String id;

	private User user;

	private boolean iSaw;

	private String comment;

	private Date date;

	private Image petImage;

	private LostPet lostPet;

	private Report[] reportList;

	private PetLocation petLocation;

	public PetCommentsDto() {

	}

	public PetCommentsDto(PetComments petComments) {
		this.user = petComments.getUser();
		this.iSaw = petComments.isiSaw();
		this.comment = petComments.getComment();
		this.date = petComments.getDate();
		this.petImage = petComments.getPetImage();
		this.lostPet = petComments.getLostPet();
		this.reportList = petComments.getReportList();
		this.petLocation = petComments.getPetLocation();
	}

	public PetCommentsDto(User user, boolean iSaw, String comment, Date date, Image petImage, LostPet lostPet,
			PetLocation petLocation) {
		this.user = user;
		this.iSaw = iSaw;
		this.comment = comment;
		this.date = date;
		this.petImage = petImage;
		this.lostPet = lostPet;
		this.petLocation = petLocation;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public LostPet getLostPet() {
		return lostPet;
	}

	public void setLostPet(LostPet lostPet) {
		this.lostPet = lostPet;
	}

	public Report[] getReportList() {
		return reportList;
	}

	public void setReportList(Report[] reportList) {
		this.reportList = reportList;
	}

	public PetLocation getPetLocation() {
		return petLocation;
	}

	public void setPetLocation(PetLocation petLocation) {
		this.petLocation = petLocation;
	}
	
	@Override
	public String toString() {
		return "PetCommentsDto [id=" + id + ", user=" + user + ", iSaw=" + iSaw + ", comment=" + comment + ", date="
				+ date + ", petImage=" + petImage + ", lostPet=" + lostPet + ", reportList=" + Arrays.toString(reportList)
				+ ", petLocation=" + petLocation + "]";
	}

}
