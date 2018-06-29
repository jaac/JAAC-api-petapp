package lpa.api.dtos;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lpa.api.documents.core.User;
import lpa.api.documents.core.Image;

public class UserDto {

	private static final String NINE_DIGITS = "\\d{9}";

	@NotNull
	@Pattern(regexp = NINE_DIGITS)
	private String mobile;

	@NotNull
	private String username;

	private String password;

	private String email;

	private String address;

	private Boolean active;

	private Date registrationDate;

	private Image image;

	public UserDto() {
		// Empty for framework
	}

	public UserDto(String mobile, String username, String password, String email, String address, Boolean active,
			Image image) {
		this.mobile = mobile;
		this.username = username;
		this.password = password;
		this.email = email;
		this.address = address;
		this.active = active;
		this.image = image;
	}

	public UserDto(String mobile) {
		this(mobile, "name" + mobile, "pass" + mobile, null, null, null, null);
	}

	public UserDto(User user) {
		this.mobile = String.valueOf(user.getMobile());
		this.username = user.getUsername();
		this.email = user.getEmail();
		this.active = user.isActive();
		this.image = user.getUserImage();
		this.registrationDate = user.getRegistrationDate();
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email != null) {
			this.email = email.toLowerCase();
		} else {
			this.email = email;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Image getUserImage() {
		return image;
	}

	public void setUserImage(Image image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "UserDto [mobile=" + mobile + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", address=" + address + ", active=" + active + ", registrationDate=" + registrationDate + "]";
	}

}
