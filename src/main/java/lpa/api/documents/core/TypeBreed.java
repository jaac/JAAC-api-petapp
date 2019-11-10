package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class TypeBreed {
    @Id
    private String id;

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


    public String getId() {
        return id;
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
    public int hashCode() {
        return this.id.hashCode();
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
                ", type=" + type.getName() +
                ", breed=" + breed.getName() +
                '}';
    }
}
