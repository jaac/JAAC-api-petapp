package lpa.api.documents.core;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class PetAttributes {
    @Id
    private String id;

    @DBRef
    private Attribute attribute;

    @DBRef
    private Pet pet;

    public PetAttributes() {
        //For Framework
    }

    public PetAttributes(Attribute attribute, Pet pet) {
        this.attribute = attribute;
        this.pet = pet;
    }

    public String getId() {
        return id;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet petId) {
        this.pet = petId;
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
        return (id.equals(((PetAttributes) obj).id));
    }


    @Override
    public String toString() {
        return "PetAttributes{" +
                "id='" + id + '\'' +
                ", attribute=" + attribute +
                ", pet=" + pet.getId() +
                '}';
    }
}
