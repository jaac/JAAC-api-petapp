package lpa.api.documents.core;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TypeBreedTest {

    @Test
    public void createTypeBreed() {
        TypeBreed typeBreed = new TypeBreed(new Type("Perro"), new Breed("Pastor"));
        assertEquals(typeBreed.getBreed().getName(), "Pastor");
        assertEquals(typeBreed.getType().getName(), "Perro");
    }


}
