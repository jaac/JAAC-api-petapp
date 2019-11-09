package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class TypeBreed {

    @Id
    private String id;
    @DBRef
    private Type petType;
    @DBRef
    private Breed breed;

    public TypeBreed() {
        //For the framework
    }

    public TypeBreed(Type petType, Breed breed) {
        this.breed = breed;
        this.petType = petType;
    }

    public String getId() {
        return id;
    }

    public Type getPetType() {
        return petType;
    }

    public void setPetType(Type petType) {
        this.petType = petType;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
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
        return (id.equals(((TypeBreed) obj).id));
    }

    @Override
    public String toString() {
        return "TypeBreed{" +
                "id='" + id + '\'' +
                ", petType=" + petType +
                ", breed=" + breed +
                '}';
    }
}
