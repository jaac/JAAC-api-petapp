package es.jaac.petlost.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.jaac.petlost.api.daos.DaoFactory;
import es.jaac.petlost.api.dtos.UserDto;
import es.jaac.petlost.api.entities.CaptGeos;
import es.jaac.petlost.api.entities.User;

public class UserController {

	public List<UserDto> userList() {
		List<User> users = DaoFactory.getFactory().getUserDao().findAll();
		List<UserDto> usersDto = new ArrayList<>();

		for (User user : users) {
			usersDto.add(new UserDto(user));
		}

		return usersDto;

	}

	public void createUser(String name) {
		List<CaptGeos> a = new ArrayList<>();
		DaoFactory.getFactory().getUserDao().create(new User(name, a));
	}

	public Optional<UserDto> readUser(int userId) {
		if (existUserId(userId)) {
			return Optional.of(new UserDto(DaoFactory.getFactory().getUserDao().read(userId)));
		} else {
			return Optional.empty();
		}
	}

	private boolean existUserId(int id) {
		return DaoFactory.getFactory().getUserDao().read(id) != null;
	}

}
