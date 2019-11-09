package lpa.api.documents.core;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PetTest {
    @Test
    public void createPet() {
        Type petType = new Type("perro");
        Breed breed = new Breed("Boxer");
        TypeBreed typeBreed = new TypeBreed(petType, breed);
        Pet pet = new Pet(true, "Nacho", Gender.MALE, 12, typeBreed);
        System.out.println(pet);
        assertNotNull(pet);
    }
}
