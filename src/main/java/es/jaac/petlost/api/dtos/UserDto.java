package es.jaac.petlost.api.dtos;

import java.util.ArrayList;
import java.util.List;

import es.jaac.petlost.api.daos.DaoFactory;
import es.jaac.petlost.api.entities.CaptGeos;
import es.jaac.petlost.api.entities.User;

public class UserDto {

	private int id;
	private String name;
	private List<CaptGeos> userCaptGeos;
	private List<CaptGeosDto> userCaptGeosDto = new ArrayList<>();

	public UserDto() {

	}

	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();

		this.userCaptGeos = DaoFactory.getFactory()
				.getCaptGeosDao()
				.findCaptGeosByUserId(this.id);
		for (CaptGeos ucg : userCaptGeos  ) {
			this.userCaptGeosDto.add(new CaptGeosDto(ucg));
		}
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CaptGeosDto> getUserCaptGeos() {

		return userCaptGeosDto;
	}


	@Override
	public String toString() {

		return "{\"id\":" + id + ",\"name\":\"" + name + "\",\"captgeos\":" + userCaptGeosDto + "}";
	}

}
