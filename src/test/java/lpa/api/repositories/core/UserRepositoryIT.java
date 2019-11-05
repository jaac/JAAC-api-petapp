package lpa.api.repositories.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.Role;
import lpa.api.documents.core.Token;
import lpa.api.documents.core.User;
import lpa.api.dtos.UserMinimumDto;
import lpa.api.repositories.core.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:test.properties")
public class UserRepositoryIT {

	@Autowired
	private UserRepository userRepository;

	private User user;

	@Before
	public void seedDb() {
		this.user = new User("666001000", "666001000", "666001000");
		this.userRepository.save(user);
	}

	@Test
	public void testfindByusername() {
		User userBd = userRepository.findByusername("666001000");
		assertNotNull(userBd);
		assertEquals("666001000", userBd.getName());
	}

	@Test
	public void testFindByTokenValue() {
		Token token = new Token();
		this.user.setToken(token);
		userRepository.save(this.user);
		assertEquals(user, userRepository.findByTokenValue(token.getValue()));
	}

	@Test
	public void testFindRegisteredAll() {
		Pageable pageable = new PageRequest(0, 20);
		Page<UserMinimumDto> userList = userRepository.findRegisteredAll(pageable);
		for (UserMinimumDto userMinimumDto : userList) {
			assertFalse(userMinimumDto.getUsername().equals("666666000"));
		}
	}

	@Test
	public void testFindByRoleAll() {
		Pageable pageable = new PageRequest(0, 20);
		Page<UserMinimumDto> userList = userRepository.findByRoles(new Role[] { Role.REGISTERED }, pageable);
		assertNotNull(userList.getContent().get(0));
	}

	@After
	public void delete() {
		this.userRepository.delete(user);
	}

}
