package es.jaac.petlost.api.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import es.jaac.petlost.api.daos.DaoFactory;
import es.jaac.petlost.api.dtos.CaptGeosDto;
import es.jaac.petlost.api.dtos.UserDto;
import es.jaac.petlost.api.entities.CaptGeos;
import es.jaac.petlost.api.resources.exceptions.UserIdNotFoundExeception;

public class CaptGeosController {

	public Optional<List<CaptGeosDto>> getCaptGeosUser(int userId) {
		if (userIdExist(userId)) {
			if (new UserDto(DaoFactory.getFactory().getUserDao().read(userId)).getUserCaptGeos() != null) {
				List<CaptGeosDto> ucgl = new UserDto(DaoFactory.getFactory().getUserDao().read(userId))
						.getUserCaptGeos();
				return Optional.of(ucgl);
			} else {
				List<CaptGeosDto> ucgl = Collections.emptyList();
				return Optional.of(ucgl);
			}

		} else {
			return Optional.empty();
		}
	}

	private boolean userIdExist(int userId) {
		return DaoFactory.getFactory().getUserDao().read(userId) != null;
	}

	public void addCaptGeo(int userId, String namecg, String imgcg, int lat, int longi)
			throws UserIdNotFoundExeception {
		DaoFactory.getFactory().getCaptGeosDao().create(new CaptGeos(namecg, imgcg, lat, longi));
		DaoFactory.getFactory().getCaptGeosDao().findCaptGeosById(1);

		if (userIdExist(userId)) {

			List<CaptGeos> cg = DaoFactory.getFactory().getCaptGeosDao().findCaptGeosById(1);

			if (new UserDto(DaoFactory.getFactory().getUserDao().read(userId)).getUserCaptGeos().toString() == "[]") {
				for (CaptGeos aa : cg) {

					if (aa.getId() == 1) {

						CaptGeos bba = aa;

						new UserDto(DaoFactory.getFactory().getUserDao().read(userId)).addUserCaptGeos(bba);
					}

				}

			} else {
				// new
				// UserDto(DaoFactory.getFactory().getUserDao().read(userId)).setUserCaptGeos(cg);
			}

		} else {
			throw new UserIdNotFoundExeception(Integer.valueOf(userId).toString());
		}
	}

	public List<CaptGeosDto> getCaptGeosList() {
		List<CaptGeos> s = DaoFactory.getFactory().getCaptGeosDao().findAll();
		List<CaptGeosDto> aa = new ArrayList<>();

		for (CaptGeos a : s) {
			aa.add(new CaptGeosDto(a));
		}
		return aa;
	}

}
