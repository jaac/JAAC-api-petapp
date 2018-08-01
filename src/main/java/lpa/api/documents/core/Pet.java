package lpa.api.documents.core;

import org.springframework.data.mongodb.core.mapping.DBRef;

public class Pet {

	@DBRef
	private PetType petType;

	private Image[] petImages;

	private String name;

	private String gender;

	private int age;

	private Breed breed;

	@DBRef
	private Color hairColor;

	@DBRef
	private Color eyesColor;

	public Pet() {
	}

	public Pet(PetType petType, Image[] petImages, String name, String gender, int age, Breed breed, Color hairColor,
			Color eyesColor) {
		this.petType = petType;
		this.petImages = petImages;
		this.name = name;
		this.gender = gender;
		this.age = age;
		this.breed = breed;
		this.hairColor = hairColor;
		this.eyesColor = eyesColor;
	}

	public Pet(PetType petType, Image[] petImages, String name, String gender) {
		this(petType, petImages, name, gender, 0, null, null, null);
	}

	public PetType getPetType() {
		return petType;
	}

	public void setPetType(PetType petType) {
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

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public Color getHairColor() {
		return hairColor;
	}

	public void setHairColor(Color hairColor) {
		this.hairColor = hairColor;
	}

	public Color getEyesColor() {
		return eyesColor;
	}

	public void setEyesColor(Color eyesColor) {
		this.eyesColor = eyesColor;
	}

	@Override
	public String toString() {
		return "Pet[petType=" + this.petType + ", petImages=" + this.petImages + ", name=" + this.name + ", gender="
				+ this.gender + ", age=" + this.age + ", breed=" + this.breed + ", hairColor=" + this.hairColor
				+ ", eyesColor=" + this.eyesColor + "]";
	}
}
