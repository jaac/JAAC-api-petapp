package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Document
public class Pet {

    @Id
    private String id;

    private int active;

    private int age;

    @DBRef
    private Breed breed;

    private String chipNumber;

    private Gender gender;

    private String name;

    @DBRef
    private Species species;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateAdd;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date dateUpd;

    public Pet() {
        //For Framework
    }

    public Pet(int active, int age, Breed breed, Gender gender, String name, Species species, String chipNumber) {
        this.active = active;
        this.age = age;
        this.breed = breed;
        this.chipNumber = chipNumber;
        this.gender = gender;
        this.name = name;
        this.species = species;
        this.dateAdd = new Date();
    }

    public Pet(int active, Gender gender, String name, Species species) {
        this(active, 0, null, gender, species.getName() + " without name", species, null);
    }

    public Pet(int active, Species species) {
        // Si la mascota introducida es desconocida
        this(active, 0, null, null, species.getName() + " without name", species, null);
    }


    public String getId() {
        return id;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
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

    public String getChipNumber() {
        return chipNumber;
    }

    public void setChipNumber(String chipNumber) {
        this.chipNumber = chipNumber;
    }

    public Date getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(Date dateAdd) {
        this.dateAdd = dateAdd;
    }

    public Date getDateUpd() {
        return dateUpd;
    }

    public void setDateUpd(Date dateUpd) {
        this.dateUpd = dateUpd;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    private String dateFormat(Date dateAdd) {
        return dateAdd != null ? new SimpleDateFormat("dd-MMM-yyyy").format(this.dateAdd.getTime()) : "null";
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
        return (id.equals(((Pet) obj).id));
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    @Override
    public String toString() {

        return "Pet{" +
                "id='" + id + '\'' +
                ", active=" + active +
                ", age=" + age +
                ", breed=" + breed +
                ", chipNumber='" + chipNumber + '\'' +
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", type=" + species +
                ", dateAdd=" + this.dateFormat(dateAdd) +
                ", dateUpd=" + this.dateFormat(dateUpd) +
                '}';
    }


}
