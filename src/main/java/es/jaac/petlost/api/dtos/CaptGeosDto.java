package es.jaac.petlost.api.dtos;

import es.jaac.petlost.api.entities.CaptGeos;

public class CaptGeosDto {
	
	private int id;
	
	private String name;
	
	private String img;
	
	private int lat;
	
	private int longi;
	
	public CaptGeosDto() {
	
	}
	
	public CaptGeosDto(CaptGeos captGeos) {
		this.id = captGeos.getId();
		this.name = captGeos.getName();
		this.img = captGeos.getImg();
		this.lat = captGeos.getLat();
		this.longi = captGeos.getLongi();
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public int getLat() {
		return lat;
	}

	public void setLat(int lat) {
		this.lat = lat;
	}

	public int getLongi() {
		return longi;
	}

	public void setLongi(int longi) {
		this.longi = longi;
	}
	
	@Override
	public String toString(){
		return "{\"id\":"+id+",\"name\":\""+name+"\",\"img\":\""+img+"\",\"lat\":"+lat+",\"long\":"+longi+"}";
	}
	
}
