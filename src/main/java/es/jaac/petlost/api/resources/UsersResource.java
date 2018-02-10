package es.jaac.petlost.api.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import es.jaac.petlost.api.dtos.CaptGeosDto;
import es.jaac.petlost.api.dtos.UserDto;
import es.jaac.petlost.api.resources.exceptions.UserFiedlInvalidExeception;
import es.jaac.petlost.api.resources.exceptions.UserIdNotFoundExeception;




public class UsersResource {

	public static final String USERS = "users";
	public static final String ID = "/{id}";
	public static final String ALLUSERS = "/allusers";

	
	public void createUser(int id,String name,int cgid, String cgname, String cgimg, int lat, int longi) throws UserFiedlInvalidExeception{
		UserDto user = new UserDto();
		CaptGeosDto captGeo = new CaptGeosDto();
		
		this.validateField(name);
		
		user.setId(id);
		user.setName(name);
		
		captGeo.setId(cgid);
		captGeo.setName(cgname);
		captGeo.setImg(cgimg);
		captGeo.setLat(lat);
		captGeo.setLongi(longi);
		List<CaptGeosDto> userCaptGeos = new ArrayList<CaptGeosDto>();
		userCaptGeos.add(captGeo);
		user.setUserCaptGeos(userCaptGeos);
		
	}
	public List<UserDto> userList(){
		
		UserDto user = new UserDto();
		user.setId(1);
		user.setName("jaac");
		List<UserDto> users = new ArrayList<UserDto>();
		users.add(user);
		return users;
	}
	
	public List<UserDto> userListEm(){
		
		UserDto user = new UserDto();
		
		
		List<UserDto> users = new ArrayList<UserDto>();
		users.add(user);
		return users;
	}
	
	



	public UserDto readUser(String userId) throws UserIdNotFoundExeception {
		UserDto user = new UserDto();
		CaptGeosDto captGeo = new CaptGeosDto();
		
		user.setId(1);
		user.setName("jaac");
		
		captGeo.setId(1);
		captGeo.setName("lostpetinNY");
		captGeo.setImg("images/img.jpg");
		captGeo.setLat(5489496);
		captGeo.setLongi(5151558);
		List<CaptGeosDto> userCaptGeos = new ArrayList<CaptGeosDto>();
		userCaptGeos.add(captGeo);
		user.setUserCaptGeos(userCaptGeos);
		Optional<UserDto> optional = Optional.of(user);

		return optional.orElseThrow(() -> new UserIdNotFoundExeception(userId));
	}

    private void validateField(String field) throws UserFiedlInvalidExeception {
        if (field == null || field.isEmpty()) {
            throw new UserFiedlInvalidExeception(field);
        }
    }

}
