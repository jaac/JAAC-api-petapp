package lpa.api.documents.core;

import org.junit.Test;

import static org.junit.Assert.assertNotNull;

public class PetTest {
    @Test
    public void createPet() {
        PetType petType = new PetType("perro");
        Pet pet = new Pet(true, "Nacho", Gender.MALE, 12, petType, new Breed("Boxer", petType));
        System.out.println(pet);
        assertNotNull(pet);
    }
}
