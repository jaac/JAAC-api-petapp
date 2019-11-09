package lpa.api.services;

import java.util.List;

import lpa.api.documents.core.Breed;
import lpa.api.documents.core.Color;
import lpa.api.documents.core.HealthCondition;
import lpa.api.documents.core.LostPet;
import lpa.api.documents.core.LostWay;
import lpa.api.documents.core.Pet;
import lpa.api.documents.core.PetComments;
import lpa.api.documents.core.Location;
import lpa.api.documents.core.Type;
import lpa.api.documents.core.Report;
import lpa.api.documents.core.Token;
import lpa.api.documents.core.User;
import lpa.api.documents.core.Image;

public class DatabaseGraph {
	private List<Image> userImageList;
	private List<User> userList;

	private List<LostPet> lostPetList;
	//
	private List<Location> locationList;
	private List<HealthCondition> healthConditionList;
	private List<Breed> breed;
	private List<Type> petTypeList;
	private List<Pet> petList;
	private List<LostWay> lostWayList;
	private List<Image> petImageList;
	private List<Color> colorList;
	private List<PetComments> petCommentsList;
	private List<Report> reportList;
	//
	private List<Token> tokenList;

	public DatabaseGraph() {
		// Empty for framework
	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public List<Token> getTokenList() {
		return tokenList;
	}

	public void setTokenList(List<Token> tokenList) {
		this.tokenList = tokenList;
	}

	public List<LostPet> getLostPetList() {
		return lostPetList;
	}

	public void setLostPetList(List<LostPet> lostPetList) {
		this.lostPetList = lostPetList;
	}

	public List<Location> getLocationList() {
		return locationList;
	}

	public void setLocationList(List<Location> locationList) {
		this.locationList = locationList;
	}

	public List<HealthCondition> getHealthConditionList() {
		return healthConditionList;
	}

	public void setHealthConditionList(List<HealthCondition> healthConditionList) {
		this.healthConditionList = healthConditionList;
	}

	public List<Breed> getBreedList() {
		return breed;
	}

	public void setBreedList(List<Breed> breed) {
		this.breed = breed;
	}

	public List<Type> getPetTypeList() {
		return petTypeList;
	}

	public void setPetTypeList(List<Type> petTypeList) {
		this.petTypeList = petTypeList;
	}

	public List<Pet> getPetList() {
		return petList;
	}

	public void setPetList(List<Pet> petList) {
		this.petList = petList;
	}

	public List<LostWay> getLostWayList() {
		return lostWayList;
	}

	public void setLostWayList(List<LostWay> lostWayList) {
		this.lostWayList = lostWayList;
	}

	public List<Image> getPetImageList() {
		return petImageList;
	}

	public void setPetImageList(List<Image> petImageList) {
		this.petImageList = petImageList;
	}

	public List<Color> getColorList() {
		return colorList;
	}

	public List<Image> getUserImageList() {
		return userImageList;
	}

	public void setUserImageList(List<Image> userImageList) {
		this.userImageList = userImageList;
	}

	public void setColorList(List<Color> colorList) {
		this.colorList = colorList;
	}


	public List<Report> getReportList() {
		return reportList;
	}

	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}

	public List<PetComments> getPetCommentsList() {
		return petCommentsList;
	}

	public void setPetCommentsList(List<PetComments> petCommentsList) {
		this.petCommentsList = petCommentsList;
	}



}
