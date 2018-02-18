package es.jaac.petlost.api.controllers;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
	UserControllerIT.class,
	CaptGeosControllerIT.class
})
public class AllPetLostApiControllersTest {

}
