package es.jaac.petlost.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.jaac.petlost.api.dtos.CaptGeosDto;
import es.jaac.petlost.api.dtos.UserDto;
import es.jaac.petlost.api.resources.exceptions.UserIdNotFoundExeception;

public class CaptGeosResource {

	public static final String USERCAPTGEO = "/userpatgeos";
	public static final String ADDCAPTGEO = "/addcaptgeo";
	
	
	
	public CaptGeosDto getCaptGeosUser(int userId) throws UserIdNotFoundExeception{
		UserDto user = new UserDto();
		
		CaptGeosDto captGeo = new CaptGeosDto();
		user.setId(1);
		user.setName("jaac");
		
		captGeo.setId(1);
		captGeo.setName("lostpetinNY");
		captGeo.setImg("images/img.jpg");
		captGeo.setLat(5489496);
		captGeo.setLongi(5151558);
		
		Optional<CaptGeosDto> optional = Optional.of(captGeo);
		
		return optional.orElseThrow(()->new UserIdNotFoundExeception(Integer.toString(userId)));
	}
	
	public void addCaptGeo(int userId) throws UserIdNotFoundExeception{
		UserDto user = new UserDto();
		CaptGeosDto captGeo = new CaptGeosDto();
		List<CaptGeosDto> cgs = new ArrayList<CaptGeosDto>();
		captGeo.setId(1);
		captGeo.setName("lostpetinNY");
		captGeo.setImg("images/img.jpg");
		captGeo.setLat(5489496);
		captGeo.setLongi(5151558);
		cgs.add(captGeo);
		user.setUserCaptGeos(cgs);
	}

}
