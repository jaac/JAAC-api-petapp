package es.jaac.petlost.api.resources;

import java.util.List;
import java.util.Optional;
import es.jaac.petlost.api.controllers.CaptGeosController;
import es.jaac.petlost.api.dtos.CaptGeosDto;
import es.jaac.petlost.api.resources.exceptions.UserIdNotFoundExeception;

public class CaptGeosResource {

	public static final String USERCAPTGEO = "userpatgeos";
	public static final String ADDCAPTGEO = "addcaptgeo";
	public static final String ID = "/{id}";

	public List<CaptGeosDto> getCaptGeosUser(int userId) throws UserIdNotFoundExeception {
		Optional<List<CaptGeosDto>> optional = new CaptGeosController().getCaptGeosUser(userId);
		return optional.orElseThrow(() -> new UserIdNotFoundExeception(Integer.toString(userId)));
	}

	public void addCaptGeo(int userId, String namecg, String imgcg, int lat, int longi)
			throws UserIdNotFoundExeception {
		new CaptGeosController().addCaptGeo(userId, namecg, imgcg, lat, longi);
	}

	public List<CaptGeosDto> getCaptGeosList() {

		
		return new CaptGeosController().getCaptGeosList();
	}

}
