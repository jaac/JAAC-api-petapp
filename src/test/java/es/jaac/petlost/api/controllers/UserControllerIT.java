package es.jaac.petlost.api.controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;

import es.jaac.petlost.api.daos.DaoFactory;
import es.jaac.petlost.api.daos.firebase.DaoFirebaseFactory;



public class UserControllerIT {

	private UserController userController;
	
    @Before
    public void before() {
        DaoFactory.setFactory(new DaoFirebaseFactory());
        userController = new UserController();
        userController.createUser("jaac");
    }
    @Test
    public void testReadUser() {
    	assertEquals("jaac",userController.readUser(1).get().getName());
    }
    @Test
    public void testReadUserNonExistId() {
    	assertFalse(userController.readUser(2).isPresent());
    }
    @Test
    public void testCreateAndUserList() {
    	userController.createUser("jaaccorp");
    	assertEquals(2,userController.userList().size());
    	assertEquals("jaac", userController.userList().get(0).getName());
    }
}
