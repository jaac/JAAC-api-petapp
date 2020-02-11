package lpa.api.dtos;

import java.util.Arrays;

import lpa.api.documents.core.Image;
import lpa.api.documents.core.Pet;

public class PetInputDto {

	private String petType;

	private Image[] petImages;

	private String name;

	private String gender;

	private int age;

	private String breed;

	private String hairColor;

	private String eyesColor;

	public PetInputDto() {

	}

	public PetInputDto(Pet pet) {

	}

	public String getPetType() {
		return petType;
	}

	public void setPetType(String petTypeId) {
		this.petType = petTypeId;
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

	public void setHairColor(String hairColorId) {
		this.hairColor = hairColorId;
	}

	public String getEyesColor() {
		return eyesColor;
	}

	public void setEyesColor(String eyesColorId) {
		this.eyesColor = eyesColorId;
	}

	@Override
	public String toString() {
		return "PetInputDto [petType=" + petType + ", petImages=" + Arrays.toString(petImages) + ", name=" + name
				+ ", gender=" + gender + ", age=" + age + ", breed=" + breed + ", hairColor=" + hairColor
				+ ", eyesColor=" + eyesColor + "]";
	}

}
