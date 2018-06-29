package lpa.api.documents.core;

public class Image {

	private String small;
	private String medium;
	private String big;

	public Image() {
		
	}

	public Image(String small, String medium, String big) {
		this.small = small;
		this.medium = medium;
		this.big = big;
	}



	public String getSmall() {
		return small;
	}

	public void setSmall(String small) {
		this.small = small;
	}

	public String getMedium() {
		return medium;
	}

	public void setMedium(String medium) {
		this.medium = medium;
	}

	public String getBig() {
		return big;
	}

	public void setBig(String big) {
		this.big = big;
	}
	



	@Override
	public String toString() {
		return "Image [ small=" + small + ", medium=" + medium + ", big=" + big + "]";
	}
}
