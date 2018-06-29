package lpa.api.dtos;

import lpa.api.documents.core.Image;

public class UserMinimumDto {
	private String mobile;

	private String username;

	private Image image;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Image getUserImage() {
		return image;
	}

	public void setUserImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserMinimumDto [mobile=" + mobile + ", username=" + username + ", image=" + image + "]";
	}
}
