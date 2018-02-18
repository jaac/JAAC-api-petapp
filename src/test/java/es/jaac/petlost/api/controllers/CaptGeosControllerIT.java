package es.jaac.petlost.api.controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.jaac.petlost.api.daos.DaoFactory;
import es.jaac.petlost.api.daos.firebase.DaoFirebaseFactory;
import es.jaac.petlost.api.resources.exceptions.UserIdNotFoundExeception;

public class CaptGeosControllerIT {

	private CaptGeosController captGeosController;
	private UserController userController;

	@Before
	public void before() {
		DaoFactory.setFactory(new DaoFirebaseFactory());
		captGeosController = new CaptGeosController();
		userController = new UserController();
	}

	@Test
	public void readUserCaptGeo() throws UserIdNotFoundExeception {
		userController.createUser("jaac");
		captGeosController.addCaptGeo(1, "lostpetinNY", "images/img.jpg", 5489496, 5151558);
		assertEquals(
				"[{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}]",
				captGeosController.getCaptGeosUser(1).get().toString());
	}
}
