package lpa.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import lpa.api.documents.core.User;
import lpa.api.repositories.core.UserRepository;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public User getCurrentUser() {
		Authentication authentication = this.getAuthentication();

		String username = authentication.getName();
		return this.userRepository.findByusername(username);
	}
}
