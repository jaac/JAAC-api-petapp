package lpa.api.resources;

import java.util.UUID;

import javax.validation.Valid;

import lpa.api.resources.exceptions.UserFieldErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lpa.api.controllers.UserController;
import lpa.api.documents.core.Role;
import lpa.api.documents.core.User;
import lpa.api.dtos.UserDto;
import lpa.api.dtos.UserMinimumDto;
import lpa.api.resources.exceptions.ForbiddenException;
import lpa.api.resources.exceptions.UserFieldAlreadyExistException;
import lpa.api.resources.exceptions.UserIdNotFoundException;

@RestController
@RequestMapping(UserResource.USERS)

public class UserResource {

	public static final String USERS = "/users";

	//public static final String USERUSERNAME = "/username";

	public static final String USER = "/{user}";

	//public static final String USERID = "/id";

	//public static final String USER_ID = "/{id}";

	public static final String USER_GET = "/get";

	public static final String CURRENT = "/current";

	@Autowired
	private UserController userController;

	@RequestMapping(method = RequestMethod.POST)
	public void createRegistered(@Valid @RequestBody UserDto userDto) throws UserFieldAlreadyExistException, UserFieldErrorException {

		if (userDto.getPassword() == null) {
			userDto.setPassword(UUID.randomUUID().toString());
		}
		if(userDto.getUsername() == null){
			throw new UserFieldErrorException("Name can´t be null");
		}
		if (this.userController.existsUsername(userDto.getUsername())) {
			throw new UserFieldAlreadyExistException("Existing username");
		}
		if (this.userController.emailRepeated(userDto)) {
			throw new UserFieldAlreadyExistException("Existing email");
		}
		System.out.println(userDto + "sssssssssssss");
		this.userController.createUser(userDto, new Role[] { Role.REGISTERED });
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR') or hasRole('REGISTERED')")
	@RequestMapping(method = RequestMethod.PUT)
	public void putUser(@Valid @RequestBody UserDto userDto)
			throws ForbiddenException, UserIdNotFoundException, UserFieldAlreadyExistException {

		if (!this.userController.existsUsername(userDto.getUsername())) {
			throw new UserIdNotFoundException("Not existing username");
		}
		if (this.userController.emailRepeated(userDto)) {
			throw new UserFieldAlreadyExistException("Existing email");
		}
		if (!this.userController.putUser(userDto)) {

			throw new ForbiddenException();
		}
	}

	@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR') or hasRole('REGISTERED')")
	@RequestMapping(value = USER, method = RequestMethod.DELETE)
	public void deleteRegistered(@PathVariable String user) throws ForbiddenException {
		if (!this.userController.deleteUser(user, new Role[] { Role.REGISTERED })) {
			throw new ForbiddenException();
		}
	}

	@RequestMapping(value = CURRENT, method = RequestMethod.GET)
	public UserMinimumDto readCurrentUser() throws UserIdNotFoundException {
		return this.userController.readCurrentUser(new Role[] { Role.REGISTERED, Role.OPERATOR, Role.ADMIN })
				.orElseThrow(() -> new UserIdNotFoundException());
	}

	@RequestMapping(value = USER, method = RequestMethod.GET)
	public UserDto readRegistered(@PathVariable String user) throws UserIdNotFoundException {
		return this.userController.readUser(user, new Role[] { Role.REGISTERED, Role.OPERATOR, Role.ADMIN })
				.orElseThrow(() -> new UserIdNotFoundException(user));
	}

	// ?
/*	@RequestMapping(value = USERID + USER_ID, method = RequestMethod.GET)
	public UserMinimumDto readUserRegistered(@PathVariable String id) throws UserIdNotFoundException {
		return this.userController.readUserbyId(id, new Role[] { Role.REGISTERED, Role.OPERATOR, Role.ADMIN })
				.orElseThrow(() -> new UserIdNotFoundException(id));
	}*/

	@RequestMapping(value = USER_GET, params = { "role", "page", "size" }, method = RequestMethod.GET)
	public Page<UserMinimumDto> readUsersByRolAll(@RequestParam("role") String role, @RequestParam("page") int page,
			@RequestParam("size") int size) {
		return this.userController.readUsersByRoleAll(role, page, size);
	}

	@RequestMapping(value = USER_GET, params = { "page", "size" }, method = RequestMethod.GET)
	public Page<User> readUsersAll(@RequestParam("page") int page, @RequestParam("size") int size) {
		return this.userController.readUsersAll(page, size);
	}

}
