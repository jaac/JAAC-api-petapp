package lpa.api.documents.core;

public class PetLocation {

	private double latitude;

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	private double longitude;

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	private String country;

	private String city;

	private String state;

	private String locality;

	private String street;

	private Number street_number;

	private int postalCode;

	private String formatted_address;

	public PetLocation() {

	}

	public PetLocation(double latitude, double longitude, String country, String city, String state, String locality,
			String street, Number street_number, String formatted_address, int postalCode) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.country = country;
		this.city = city;
		this.state = state;
		this.locality = locality;
		this.street = street;
		this.street_number = street_number;
		this.formatted_address = formatted_address;
		this.postalCode = postalCode;
	}

	public PetLocation(double latitude, double longitude) {
		this(latitude, longitude, null, null, null, null, null, 0, null, 0);
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

		return "PetLocation [latitude=" + this.latitude + ", longitude=" + this.longitude
				+ ", country=" + this.country + ", city=" + this.city + ", state=" + this.state + ", locality="
				+ this.locality + ", street=" + this.street + ", street_number=" + this.street_number
				+ ", formatted_address=" + this.formatted_address + ", postalCode=" + this.postalCode + "]";
	}

}
