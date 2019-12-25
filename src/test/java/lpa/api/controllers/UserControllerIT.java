package lpa.api.controllers;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.controllers.UserController;

import lpa.api.dtos.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserControllerIT {
	@Autowired
	private UserController userController;

	@Test
	public void testEmailRepeatedFalse() {
		UserDto userDto = new UserDto("666666001", null, null, "u001@gmail.com", null, null);
		assertFalse(userController.emailRepeated(userDto));
	}

	@Test
	public void testEmailRepeatedTrue() {
		UserDto userDto = new UserDto("666666001", null, null, "u004@gmail.com", null, null);
		assertTrue(userController.emailRepeated(userDto));
	}

}
