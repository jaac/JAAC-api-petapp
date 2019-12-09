package lpa.api.controllers;

import java.util.Arrays;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;

import lpa.api.documents.core.Role;
import lpa.api.documents.core.User;
import lpa.api.dtos.UserDto;
import lpa.api.dtos.UserMinimumDto;
import lpa.api.repositories.core.UserRepository;
import lpa.api.services.AuthenticationFacade;

@Controller
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationFacade authenticationFacade;

    public void createUser(UserDto userDto, Role[] roles) {
        User user = new User(userDto.getUsername(), userDto.getPassword(), userDto.getEmail());
        user.setRoles(roles);
        this.userRepository.save(user);
    }

    public boolean emailRepeated(UserDto userDto) {
        User user = this.userRepository.findByEmail(userDto.getEmail());
        return userDto.getEmail() != null && user != null && !user.getUsername().equals(userDto.getUsername());
    }

    public boolean existsUsername(String username) {
        return this.userRepository.findByusername(username) != null;
    }

    public boolean putUser(UserDto userDto) {
        User user = this.userRepository.findByusername(userDto.getUsername());
        assert user != null;
        Role[] roles = new Role[]{Role.ADMIN, Role.OPERATOR, Role.MANAGER};

        if (Arrays.asList(roles).containsAll(Arrays.asList(this.authenticationFacade.getCurrentUser().getRoles()))
                || this.authenticationFacade.getCurrentUser().equals(user)) {
            user.setEmail(userDto.getEmail());
            user.setActive(userDto.isActive());
            user.setDateUpd(new Date());
            //user.setUserImage(userDto.getUserImage());
            this.userRepository.save(user);
        } else {
            return false;
        }
        return true;
    }

    public boolean deleteUser(String username, Role[] roles) {
        User userBd = this.userRepository.findByusername(username);
        if (userBd == null) {
            return true;
        } else if (Arrays.asList(roles).containsAll(Arrays.asList(userBd.getRoles()))) {
            this.userRepository.delete(userBd);
            return true;
        } else {
            return false;
        }
    }

    /*Lee el usuario por id o nombre de usuario*/
    public Optional<UserDto> readUser(String user, Role[] roles) {
        User userByName = this.userRepository.findByusername(user);
        User userById = this.userRepository.findById(user);
        if (userByName != null || userById != null) {
        	if(userByName != null && Arrays.asList(roles).containsAll(Arrays.asList(userByName.getRoles()))){
				return Optional.of(new UserDto(userByName));
			}
        	if( userById != null && Arrays.asList(roles).containsAll(Arrays.asList(userById.getRoles()))) {
				return Optional.of(new UserDto(userById));
			}
        }
        return Optional.empty();
    }

/*    public Optional<UserMinimumDto> readUserbyId(String id, Role[] roles) {
        User userBd = this.userRepository.findById(id);
        if (userBd == null) {
            return Optional.empty();
        } else if (Arrays.asList(roles).containsAll(Arrays.asList(userBd.getRoles()))) {
            return Optional.of(new UserMinimumDto(userBd));
        } else {
            return Optional.empty();
        }
    }*/

    public Page<User> readRegisteredAll(int page, int limit) {
        return this.userRepository.findByRoles(new Role[]{Role.REGISTERED},new PageRequest(page, limit));
    }

    public Page<UserMinimumDto> readUsersByRoleAll(String role, int page, int limit) {
        Role[] rolUpercase = new Role[]{Role.valueOf(role.toUpperCase())};
		System.out.println(new Role[]{Role.valueOf(role.toUpperCase())});
        return null;
    }

    public Page<User> readUsersAll(int page, int size) {
        // Encontar manera de paginar usuarios en DTO
        Page<User> users = this.userRepository.findAll(new PageRequest(page, size));
        return users;
    }

    public Optional<UserMinimumDto> readCurrentUser(Role[] roles) {
        if (this.authenticationFacade.getCurrentUser() != null) {
            return Optional.of(new UserMinimumDto(this.authenticationFacade.getCurrentUser()));
        } else {
            UserMinimumDto guest = new UserMinimumDto();
            guest.setUsername("guest");
            return Optional.of(guest);
        }

    }

}
