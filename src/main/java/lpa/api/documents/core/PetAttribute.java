package lpa.api.documents.core;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class PetAttribute {

    @DBRef
    private Pet pet;

    @DBRef
    private Attribute attribute;


    public PetAttribute() {
    }

    public PetAttribute(Pet pet, Attribute attribute) {
        this.pet = pet;
        this.attribute = attribute;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public void setAttribute(Attribute attribute) {
        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PetAttribute that = (PetAttribute) o;
        return Objects.equals(pet, that.pet) &&
                Objects.equals(attribute, that.attribute);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pet, attribute);
    }

    @Override
    public String toString() {
        return "PetAttribute{" +
                "pet=" + pet.getName() +
                ", attribute=" + attribute.getName() +
                '}';
    }
}
