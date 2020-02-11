package lpa.api.documents.core;

import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class SpeciesBreed {

    @DBRef
    private Species species;

    @DBRef
    private Breed breed;

    public SpeciesBreed() {
    }

    public SpeciesBreed(Species species, Breed breed) {
        this.species = species;
        this.breed = breed;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
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
        return (species.equals(((SpeciesBreed) obj).species) && breed.equals(((SpeciesBreed) obj).breed));
    }

    @Override
    public String toString() {
        return "TypeBreed{" +
                ", type=" + species.getName() +
                ", breed=" + breed.getName() +
                '}';
    }
}
