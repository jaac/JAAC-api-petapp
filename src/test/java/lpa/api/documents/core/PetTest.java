package lpa.api.documents.core;

import org.junit.Assert;
import org.junit.Test;

public class PetTest {
    @Test
    public void createPet() {
        Type petType = new Type("perro");
        Breed breed = new Breed("Boxer");
        TypeBreed typeBreed = new TypeBreed(petType, breed);
        Pet pet = new Pet(true, "Nacho", Gender.MALE, 12, typeBreed);
        Assert.assertEquals("Pet{id='null', typeBreed=TypeBreed{id='null', " +
                "type=Type{id= null, name= perro}, breed=Breed{id='null', name='Boxer'}}, " +
                "active=true, name='Nacho', gender=MALE, age=12}", pet.toString());
    }
}
