package es.jaac.petlost.api.dtos;

import java.util.ArrayList;
import java.util.List;

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
		this.userCaptGeos = user.getCaptgeos();
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
		// capturar cg y convertirlos en cgdto
		for (CaptGeos cg : this.userCaptGeos) {
			this.userCaptGeosDto.add(new CaptGeosDto(cg));
		}
		return userCaptGeosDto;
	}

	public void addUserCaptGeos(CaptGeos userCaptGeos) {

		this.userCaptGeos.add(userCaptGeos);
	}

	@Override
	public String toString() {

		return "{\"id\":" + id + ",\"name\":\"" + name + "\",\"captgeos\":" + userCaptGeosDto + "}";
	}

}
