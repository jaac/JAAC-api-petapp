package lpa.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lpa.api.documents.core.Role;
import lpa.api.documents.core.User;
import lpa.api.repositories.core.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

	public static final String P_TOKEN = "";

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(final String usernameOrTokenValue) {
		User user = userRepository.findByTokenValue(usernameOrTokenValue);
		if (user != null) {
			return this.userBuilder(user.getUsername(), new BCryptPasswordEncoder().encode(P_TOKEN), user.getRoles(),
					user.isActive());
		} else {
			user = userRepository.findByusername(usernameOrTokenValue);
			if (user != null) {
				return this.userBuilder(String.valueOf(user.getUsername()), user.getPassword(),
						new Role[] { Role.REGISTERED }, user.isActive());
			} else {
				throw new UsernameNotFoundException("Username-token not found. " + usernameOrTokenValue);
			}
		}
	}

	private org.springframework.security.core.userdetails.User userBuilder(String username, String password,
			Role[] roles, boolean active) {
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;

		boolean enabled = active;
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.roleName()));
		}
		return new org.springframework.security.core.userdetails.User(username, password, enabled, accountNonExpired,
				credentialsNonExpired, accountNonLocked, authorities);
	}

}
