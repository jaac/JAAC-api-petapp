package es.jaac.petlost.api.entities;

public class CaptGeos {
	
	private int id;
	
	private String name;
	
	private String img;
	
	private int lat;
	
	private int longi;
	
	private int userId = 0;
	
	public CaptGeos() {
		
	}
	
	public CaptGeos(int userId, String name, String img, int lat, int longi) {
		this.name = name;
		this.userId = userId;
		this.img = img;
		this.lat = lat;
		this.longi = longi;
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
		return "CaptGeo[id="+ id + ", name=" + name + ", img = "+ img +", lat="+ lat +", longi = " + longi + "]";
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}
	
}
