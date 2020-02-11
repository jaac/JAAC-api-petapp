package lpa.api.controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ LostPetControllerIT.class, PetSpeciesControllerIT.class, UserControllerIT.class })
public class AllLostPetControllersIntegrationTests {

}
