package es.jaac.petlost.api.dtos;
import java.util.List;

import es.jaac.petlost.api.entities.User;
public class UserDto {
	
	private int id;
	private String name;
	private List<CaptGeosDto> userCaptGeos;
	
	public UserDto(){
		
	}
	
	public UserDto(User user){
		this.id = user.getId();
		this.name = user.getName();
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
		return userCaptGeos;
	}

	public void setUserCaptGeos(List<CaptGeosDto> userCaptGeos) {
		this.userCaptGeos = userCaptGeos;
	}
	
    @Override
    public String toString() {
    	
        return "{\"id\":"+ id +",\"name\":\""+name+"\",\"captgeos\":"+userCaptGeos+"}";
    }
    
}