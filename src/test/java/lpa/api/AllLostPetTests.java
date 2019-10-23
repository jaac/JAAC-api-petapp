package lpa.api;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import lpa.api.controllers.AllLostPetControllersIntegrationTests;
import lpa.api.documents.core.AllLostPetDocumentsCoreTests;
import lpa.api.repositories.core.AllLostPetRepositoiesTests;
import lpa.api.resources.AllLostPetResourcesIntegrationTests;
import lpa.api.services.AllLostPetServicersIntegrationTests;

@RunWith(Suite.class)
@SuiteClasses({ AllLostPetControllersIntegrationTests.class, AllLostPetDocumentsCoreTests.class,
		AllLostPetRepositoiesTests.class, AllLostPetResourcesIntegrationTests.class,
		AllLostPetServicersIntegrationTests.class })
public class AllLostPetTests {

}
