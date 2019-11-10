package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class Pet {

    @Id
    private String id;

    private int active;

    private int age;

    @DBRef
    private Breed breed;

    private Gender gender;

    private String name;

    @DBRef
    private Type type;


    public Pet() {
        //For Framework
    }

    public Pet(int active, int age, Breed breed, Gender gender, String name, Type type) {
        this.active = active;
        this.age = age;
        this.breed = breed;
        this.gender = gender;
        this.name = name;
        this.type = type;
    }

    public Pet(int active, Gender gender, String name, Type type) {
        this(active, 0, null, gender, type.getName() + " without name", type);
    }

    public Pet(int active, Type type) {
        // Si la mascota introducida es desconocida
        this(active, 0, null, null, type.getName() + " without name", type);
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

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
                ", gender=" + gender +
                ", name='" + name + '\'' +
                ", type=" + type +
                '}';
    }
}
