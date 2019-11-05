package lpa.api.documents.core;

public class Image {

	private String image;

	public Image() {

	}

	public Image(String image) {
		this.image = image;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Image [image=" + image + "]";
	}
}
