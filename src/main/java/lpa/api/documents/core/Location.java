package lpa.api.documents.core;

public class Location {
	
	private String type;
	
	private double[] coordinates;

	private String country;

	private String city;

	private String state;

	private String locality;

	private String street;

	private Number street_number;

	private int postalCode;

	private String formatted_address;

	public Location() {
		this.setType("Point");
	}

	public Location(double[] coordinates, String country, String city, String state, String locality,
			String street, Number street_number, String formatted_address, int postalCode) {
		this();
		this.coordinates = coordinates;
		this.country = country;
		this.city = city;
		this.state = state;
		this.locality = locality;
		this.street = street;
		this.street_number = street_number;
		this.formatted_address = formatted_address;
		this.postalCode = postalCode;
	}

	public Location(double[] coordinates) {
		this(coordinates, null, null, null, null, null, 0, null, 0);
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double[] getcoordinates() {
		return coordinates;
	}

	public void setcoordinates(double[] coordinates) {
		this.coordinates = coordinates;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public String getLocality() {
		return locality;
	}

	public void setLocality(String locality) {
		this.locality = locality;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Number getStreet_number() {
		return street_number;
	}

	public void setStreet_number(Number street_number) {
		this.street_number = street_number;
	}

	public String getFormatted_address() {
		return formatted_address;
	}

	public void setFormatted_address(String formatted_address) {
		this.formatted_address = formatted_address;
	}

	@Override
	public String toString() {

		return "Location [coordinates=" + this.coordinates + ", country=" + this.country
				+ ", city=" + this.city + ", state=" + this.state + ", locality=" + this.locality + ", street="
				+ this.street + ", street_number=" + this.street_number + ", formatted_address="
				+ this.formatted_address + ", postalCode=" + this.postalCode + "]";
	}

}
