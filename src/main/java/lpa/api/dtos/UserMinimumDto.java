package lpa.api.dtos;

import lpa.api.documents.core.Image;
import lpa.api.documents.core.User;

public class UserMinimumDto {
	
	private String username;

	private String name;

	private Image image;

	private String id;
	
	public UserMinimumDto() {}

	public UserMinimumDto(User userBd) {
		this.setId(userBd.getId());
		this.username = userBd.getUsername();
		this.name = userBd.getName();
		this.image = userBd.getUserImage();
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "UserMinimumDto [username=" + username + ", name=" + name + ", image=" + image + "]";
	}
}
