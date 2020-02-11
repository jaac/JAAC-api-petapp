package lpa.api.dtos;

import java.util.Arrays;

import lpa.api.documents.core.Image;
import lpa.api.documents.core.Pet;

public class PetOutPutDto {

	private String petType;
	private Image[] petImages;
	private String name;
	private String gender;
	private int age;
	private String breed;
	private String hairColor;
	private String eyesColor;

	public PetOutPutDto() {
		// Empty for FrameWork
	}

	public PetOutPutDto(Pet pet) {
		this.petType = pet.getSpecies().getName();
		//this.petImages = pet.getPetImages();
		this.name = pet.getName();
		//this.gender = pet.getGender();
		this.age = pet.getAge();
		this.breed = pet.getBreed().getName();
		//this.hairColor = pet.getHairColor().getName();
		//this.eyesColor = pet.getEyesColor().getName();
	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petType) {
		this.petType = petType;
	}

	public Image[] getPetImages() {
		return petImages;
	}

	public void setPetImages(Image[] petImages) {
		this.petImages = petImages;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getHairColor() {
		return hairColor;
	}

	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}

	public String getEyesColor() {
		return eyesColor;
	}

	public void setEyesColor(String eyesColor) {
		this.eyesColor = eyesColor;
	}

	@Override
	public String toString() {
		return "PetOutPutDto [petType=" + petType + ", petImages=" + Arrays.toString(petImages) + ", name=" + name
				+ ", gender=" + gender + ", age=" + age + ", breed=" + breed + ", hairColor=" + hairColor
				+ ", eyesColor=" + eyesColor + "]";
	}

}
