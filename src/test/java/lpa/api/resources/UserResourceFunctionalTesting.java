package lpa.api.resources;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import lpa.api.documents.core.User;
import lpa.api.dtos.UserDto;
import lpa.api.dtos.UserMinimumDto;
import lpa.api.repositories.core.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class UserResourceFunctionalTesting {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private RestService restService;

	private UserDto userDto;

	@Autowired
	private UserRepository userRepository;

	@Before
	public void before() {
		this.userDto = new UserDto("666000000");
	}

	@Test
	public void testCreateRegistered() {
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(this.userDto).post().build();
	}

	@Test
	public void testCreateRegisteredPassNull() {
		this.userDto.setPassword(null);
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(this.userDto).post().build();
	}

	@Test
	public void testCreateRegisteredWithoutUserException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.userDto.setName(null);
		restService.loginAdmin().restBuilder().path(UserResource.USERS).post().build();
	}

	@Test
	public void testCreateRegisteredUsernameNullException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.userDto.setName(null);
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(this.userDto).post().build();
	}

	@Test
	public void testCreateRegisteredusernamePatternException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.userDto.setUsername("ds sd");
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(userDto).post().build();
	}

	@Test
	public void testCreateRegisteredusernameRepeatUserFieldAlreadyExistException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(userDto).post().build();
		restService.restBuilder().path(UserResource.USERS).body(userDto).post().build();
	}

	@Test
	public void testCreateRegisteredEmailRepeatUserFieldAlreadyExistException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.userDto.setEmail("repeat@gmail.com");
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(this.userDto).post().build();
		UserDto userDto2 = new UserDto("6660000002");
		userDto2.setEmail("repeat@gmail.com");
		restService.restBuilder().path(UserResource.USERS).body(userDto2).post().build();
	}

	@Test
	public void testCreateUnauthorized() {
		thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
		restService.logout().restBuilder().path(UserResource.USERS).body(userDto).post().build();
	}

	@Test
	public void testReadUser() {
		restService.loginAdmin().restBuilder().path(UserResource.USERS).path(UserResource.USERUSERNAME)
				.path(UserResource.USERNAME_ID).expand(666666002).get().build();
	}

	@Test
	public void testReadUsersByRoll() {
		restService.loginAdmin().restBuilder().path(UserResource.USERS).path(UserResource.USER_GET)
				.param("role", "admin").param("page", "0").param("size", "20").get().build();
	}

	@Test
	public void testReadUsersAll() {
		restService.loginAdmin().restBuilder().path(UserResource.USERS).path(UserResource.USER_GET).param("page", "0")
				.param("size", "20").get().build();
	}

	@Test
	public void testPutUserOwner() {
		User user = this.userRepository.findByusername("666666002");
		UserDto userDto1 = new UserDto(user);
		restService.loginRegistered().restBuilder().path(UserResource.USERS).body(userDto1).put().build();
	}

	@Test
	public void testPutUserAsAdmin() {
		User user = this.userRepository.findByusername("666666002");
		UserDto userDto1 = new UserDto(user);
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(userDto1).put().build();
	}

	@Test
	public void testReadUserNotRol() {
		thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
		restService.loginAdmin().restBuilder().path(UserResource.USERS).path(UserResource.USERUSERNAME)
				.path(UserResource.USERNAME_ID).expand(666666001).get().build();
	}

	@Test
	public void testReadUserUnauthorized() {
		thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
		restService.logout().restBuilder().path(UserResource.USERS).path(UserResource.USERUSERNAME)
				.path(UserResource.USERNAME_ID).expand(666666001).get().build();
	}

	@Test
	public void testReadCurrentUser() {

		UserMinimumDto userMinimumDto = restService.loginRegistered().restBuilder(new RestBuilder<UserMinimumDto>())
				.clazz(UserMinimumDto.class).path(UserResource.USERS).path(UserResource.CURRENT).get().build();
		assertEquals("u002", userMinimumDto.getName());
	}

	@After
	public void delete() {
		this.restService.loginAdmin();
		restService.restBuilder().path(UserResource.USERS).path(UserResource.USERNAME_ID)
				.expand(this.userDto.getUsername()).delete().build();
	}

}
