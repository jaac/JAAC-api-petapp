package lpa.api.dtos;

import lpa.api.documents.core.Image;
import lpa.api.documents.core.User;

public class UserMinimumDto {
	
	private String username;

	private Image image;

	private String id;
	
	public UserMinimumDto() {}

	public UserMinimumDto(User userBd) {
		this.setId(userBd.getId());
		this.username = userBd.getUsername();
		//this.image = userBd.getUserImage();
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserMinimumDto [username=" + username + ", image=" + image + "]";
	}
}
