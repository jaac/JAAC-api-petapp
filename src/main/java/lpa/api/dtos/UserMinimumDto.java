package lpa.api.dtos;

import lpa.api.documents.core.Image;

public class UserMinimumDto {
	private String username;

	private String name;

	private Image image;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Image getUserImage() {
		return image;
	}

	public void setUserImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserMinimumDto [username=" + username + ", name=" + name + ", image=" + image + "]";
	}
}
