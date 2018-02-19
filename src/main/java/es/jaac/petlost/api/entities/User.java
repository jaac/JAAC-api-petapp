package es.jaac.petlost.api.entities;

import java.util.List;

public class User {

	private int id;
	private String name;
	private List<CaptGeos> captgeos;
	
	public User() {
		
	}
	
	public User(String name, List<CaptGeos> captgeos) {
		this.name = name;
		this.captgeos = captgeos;
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

	public List<CaptGeos> getCaptgeos() {
		return captgeos;
	}

	public void setCaptgeos(List<CaptGeos> captgeos) {
		this.captgeos = captgeos;
	}
    
    @Override
    public String toString(){
    	
    	return "User[id="+ id + ", name=" + name + ", captgeos="+ captgeos +"]";
    }

	public void addCaptgeos(CaptGeos userCaptGeos) {
		
		this.captgeos.add(userCaptGeos);
	}

}
