package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TypeBreed {

    @DBRef
    private Type type;

    @DBRef
    private Breed breed;

    public TypeBreed() {
    }

    public TypeBreed(Type type, Breed breed) {
        this.type = type;
        this.breed = breed;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
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
        return (type.equals(((TypeBreed) obj).type) && breed.equals(((TypeBreed) obj).breed));
    }

    @Override
    public String toString() {
        return "TypeBreed{" +
                ", type=" + type.getName() +
                ", breed=" + breed.getName() +
                '}';
    }
}
