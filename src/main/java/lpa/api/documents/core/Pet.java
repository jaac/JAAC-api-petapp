package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Pet {

    @Id
    private String id;

    @DBRef
    private TypeBreed typeBreed;

    private boolean active;

    private String name;

    private Gender gender;

    private int age;

    public Pet() {
    }

    public Pet(boolean active, String name, Gender gender, int age, TypeBreed typeBreed) {
        this.active = active;
        this.typeBreed = typeBreed;
        this.name = name;
        this.gender = gender;
        this.age = age;
    }

    public Pet(boolean active, TypeBreed typeBreed) {
        // Si la mascota introducida es desconocida
        this(active, typeBreed.getPetType().getName() + " without name", Gender.UNKNOWN, 0, typeBreed);
    }

    public String getId() {
        return id;
    }

    public TypeBreed getTypeBreed() {
        return typeBreed;
    }

    public void setTypeBreed(TypeBreed typeBreed) {
        this.typeBreed = typeBreed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id='" + id + '\'' +
                ", typeBreed=" + typeBreed +
                ", active=" + active +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                '}';
    }
}
