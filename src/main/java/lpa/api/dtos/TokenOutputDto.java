package lpa.api.dtos;

import java.util.Arrays;

import lpa.api.documents.core.Role;
import lpa.api.documents.core.User;

public class TokenOutputDto {
	private String token;

	private Role[] roles;

	public TokenOutputDto() {
		// Empty for framework
	}

	public TokenOutputDto(User user) {
		this.token = user.getToken().getValue();
		this.roles = user.getRoles();
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Role[] getRoles() {
		return roles;
	}

	public void setRoles(Role[] roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "TokenDto [token=" + token + ", roles=" + Arrays.toString(roles) + "]";
	}
}
