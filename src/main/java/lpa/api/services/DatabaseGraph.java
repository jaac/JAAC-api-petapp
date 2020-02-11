package lpa.api.services;

import java.util.List;

import lpa.api.documents.core.*;

public class DatabaseGraph {
	private List<Image> imageList;
	private List<User> userList;

	private List<LostPet> lostPetList;
	//
	private List<Location> locationList;
	private List<HealthCondition> healthConditionList;
	private List<Breed> breedList;
	private List<Species> speciesList;
	private List<Pet> petList;
	private List<LostWay> lostWayList;
	private List<Comments> commentsList;
	private List<Report> reportList;
	private List<AttributesCategory> attributesCategoryList;
	private List<SpeciesBreed> speciesBreedList;
	private List<UserProfile> userProfileList;
	private List<Attribute> attributesList;
	private List<Token> tokenList;


	public DatabaseGraph() {
		// Empty for framework
	}

	public List<SpeciesBreed> getSpeciesBreedList() {
		return speciesBreedList;
	}

	public List<Attribute> getAttributesList() {
		return attributesList;
	}

	public void setAttributesList(List<Attribute> attributesList) {
		this.attributesList = attributesList;
	}

	public void setSpeciesBreedList(List<SpeciesBreed> speciesBreedList) {
		this.speciesBreedList = speciesBreedList;
	}

	public List<AttributesCategory> getAttributesCategoryList() {
		return attributesCategoryList;
	}

	public void setAttributesCategoryList(List<AttributesCategory> attributesCategoryList) {
		this.attributesCategoryList = attributesCategoryList;
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

	public List<Breed> getBreedList() {
		return breedList;
	}

	public void setBreedList(List<Breed> breed) {
		this.breedList = breed;
	}

	public List<Species> getSpeciesList() {
		return speciesList;
	}

	public void setSpeciesList(List<Species> speciesList) {
		this.speciesList = speciesList;
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

	public List<Image> getImageList() {
		return imageList;
	}

	public void setImageList(List<Image> imageList) {
		this.imageList = imageList;
	}

	public List<Report> getReportList() {
		return reportList;
	}

	public void setReportList(List<Report> reportList) {
		this.reportList = reportList;
	}

	public List<Comments> getCommentsList() {
		return commentsList;
	}

	public void setCommentsList(List<Comments> commentsList) {
		this.commentsList = commentsList;
	}


	public List<UserProfile> getUserProfileList() {
		return userProfileList;
	}

	public void setUserProfileList(List<UserProfile> userProfileList) {
		this.userProfileList = userProfileList;
	}
}
