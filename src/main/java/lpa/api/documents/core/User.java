package lpa.api.documents.core;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Document
public class User {

	@Id
	private String id;

	@Indexed(unique = true)
	private String username;

	private String name;

	private String password;

	private boolean active;

	private String email;

	private Date registrationDate;

	private Role[] roles;

	private Token token;

	private Image image;

	public User() {
		this.registrationDate = new Date();
		this.active = true;
	}

	public User(String username, String name, String password, String email, Image image) {
		this();
		this.username = username;
		this.name = name;
		this.email = email;
		this.setPassword(password);
		this.setUserImage(image);
		this.roles = new Role[] { Role.REGISTERED };
	}

	public User(String username, String name, String password, String email) {
		this(username, username, password, email, null);
	}

	public User(String username, String name, String password) {
		this(username, username, password, null, null);
	}

	public String getId() {
		return id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = new BCryptPasswordEncoder().encode(password);
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Role[] getRoles() {
		return roles;
	}

	public void setRoles(Role[] roles) {
		this.roles = roles;
	}

	public Token getToken() {
		return token;
	}

	public void setToken(Token token) {
		this.token = token;
	}

	public Image getUserImage() {
		return image;
	}

	public void setUserImage(Image image) {
		this.image = image;
	}

	@Override
	public int hashCode() {
		return this.username.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		return username.equals(((User) obj).username);
	}

	@Override
	public String toString() {
		String date = "null";
		if (registrationDate != null) {
			date = new SimpleDateFormat("dd-MMM-yyyy ").format(registrationDate.getTime());
		}
		return "User [username=" + username + ", name=" + name + ", password=" + password + ", active=" + active
				+ ", email=" + email + ", image=" + image + ", registrationDate=" + date + ", roles="
				+ java.util.Arrays.toString(roles) + ", token=" + token + "]";
	}

}
