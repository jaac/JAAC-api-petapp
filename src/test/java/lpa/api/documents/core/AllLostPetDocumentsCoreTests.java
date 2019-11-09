package lpa.api.documents.core;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({PetLostTest.class, PetTest.class, AttributeTest.class,
        AttributesCategoryTest.class, PetAttributesTest.class, TypeBreedTest.class,
        PetLostCommentsTest.class})
public class AllLostPetDocumentsCoreTests {

}
