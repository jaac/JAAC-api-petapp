package lpa.api.resources;

import java.util.List;
import java.util.UUID;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import lpa.api.controllers.UserController;
import lpa.api.documents.core.Role;
import lpa.api.dtos.UserDto;
import lpa.api.dtos.UserMinimumDto;
import lpa.api.resources.exceptions.ForbiddenException;
import lpa.api.resources.exceptions.UserFieldAlreadyExistException;
import lpa.api.resources.exceptions.UserIdNotFoundException;

@PreAuthorize("hasRole('ADMIN') or hasRole('MANAGER') or hasRole('OPERATOR')")
@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {

	public static final String USERS = "/users";

	public static final String USERMOBILE = "/mobile";

	public static final String MOBILE_ID = "/{mobile}";

	public static final String USERID = "/id";

	public static final String USER_ID = "/{id}";

	@Autowired
	private UserController userController;

	@RequestMapping(method = RequestMethod.POST)
	public void createCustomer(@Valid @RequestBody UserDto userDto) throws UserFieldAlreadyExistException {
		if (userDto.getPassword() == null) {
			userDto.setPassword(UUID.randomUUID().toString());
		}
		if (this.userController.existsMobile(userDto.getMobile())) {
			throw new UserFieldAlreadyExistException("Existing mobile");
		}
		if (this.userController.emailRepeated(userDto)) {
			throw new UserFieldAlreadyExistException("Existing email");
		}
		this.userController.createUser(userDto, new Role[] { Role.REGISTERED });
	}

	@RequestMapping(method = RequestMethod.PUT)
	public void putCustomer(@Valid @RequestBody UserDto userDto)
			throws ForbiddenException, UserIdNotFoundException, UserFieldAlreadyExistException {
		if (!this.userController.existsMobile(userDto.getMobile())) {
			throw new UserIdNotFoundException("Not existing mobile");
		}
		if (this.userController.emailRepeated(userDto)) {
			throw new UserFieldAlreadyExistException("Existing email");
		}

		if (!this.userController.putUser(userDto, new Role[] { Role.REGISTERED })) {
			throw new ForbiddenException();
		}
	}

	@RequestMapping(value = MOBILE_ID, method = RequestMethod.DELETE)
	public void deleteCustomer(@PathVariable String mobile) throws ForbiddenException {
		if (!this.userController.deleteUser(mobile, new Role[] { Role.REGISTERED })) {
			throw new ForbiddenException();
		}
	}

	@RequestMapping(value = USERMOBILE + MOBILE_ID, method = RequestMethod.GET)
	public UserDto readCustomer(@PathVariable String mobile) throws UserIdNotFoundException {
		return this.userController.readUser(mobile, new Role[] { Role.REGISTERED })
				.orElseThrow(() -> new UserIdNotFoundException(mobile));
	}

	@RequestMapping(value = USERID + USER_ID, method = RequestMethod.GET)
	public UserDto readUserRegistered(@PathVariable String id) throws UserIdNotFoundException {
		return this.userController.readUserbyId(id, new Role[] { Role.REGISTERED })
				.orElseThrow(() -> new UserIdNotFoundException(id));
	}

	@RequestMapping(method = RequestMethod.GET)
	public List<UserMinimumDto> readCustomerAll() {
		return this.userController.readCustomerAll();
	}

}
