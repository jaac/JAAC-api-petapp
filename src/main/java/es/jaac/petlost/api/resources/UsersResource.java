package es.jaac.petlost.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.jaac.petlost.api.controllers.UserController;
import es.jaac.petlost.api.dtos.UserDto;
import es.jaac.petlost.api.resources.exceptions.UserFiedlInvalidExeception;
import es.jaac.petlost.api.resources.exceptions.UserIdNotFoundExeception;

public class UsersResource {

	public static final String USERS = "users";
	public static final String ID = "/{id}";
	public static final String ALLUSERS = "/allusers";

	public void createUser(String name) throws UserFiedlInvalidExeception {

		this.validateField(name);
		new UserController().createUser(name);

	}

	public List<UserDto> userList() {
		return new UserController().userList();
	}

	public List<UserDto> userListEm() {

		UserDto user = new UserDto();

		List<UserDto> users = new ArrayList<UserDto>();
		users.add(user);
		return users;
	}

	public UserDto readUser(int userId) throws UserIdNotFoundExeception {
		Optional<UserDto> optional = new UserController().readUser(userId);
		return optional.orElseThrow(() -> new UserIdNotFoundExeception(Integer.valueOf(userId).toString()));
	}

	private void validateField(String field) throws UserFiedlInvalidExeception {
		if (field == null || field.isEmpty()) {
			throw new UserFiedlInvalidExeception(field);
		}
	}

}
