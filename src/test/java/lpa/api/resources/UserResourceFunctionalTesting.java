package lpa.api.resources;

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

import lpa.api.dtos.UserDto;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "classpath:test.properties")
public class UserResourceFunctionalTesting {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private RestService restService;

	private UserDto userDto;

	@Before
	public void before() {
		this.userDto = new UserDto("666000000");
	}

	@Test
	public void testCreateCustomer() {
		System.out.println(this.userDto);
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(this.userDto).post().build();
	}

	@Test
	public void testCreateCustomerPassNull() {
		this.userDto.setPassword(null);
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(this.userDto).post().build();
	}

	@Test
	public void testCreateCustomerWithoutUserException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.userDto.setUsername(null);
		restService.loginAdmin().restBuilder().path(UserResource.USERS).post().build();
	}

	@Test
	public void testCreateCustomerUsernameNullException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.userDto.setUsername(null);
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(this.userDto).post().build();
	}

	@Test
	public void testCreateCustomerMobilePatternException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		this.userDto.setMobile("123");
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(userDto).post().build();
	}

	@Test
	public void testCreateCustomerMobileRepeatUserFieldAlreadyExistException() {
		thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
		restService.loginAdmin().restBuilder().path(UserResource.USERS).body(userDto).post().build();
		restService.restBuilder().path(UserResource.USERS).body(userDto).post().build();
	}

	@Test
	public void testCreateCustomerEmailRepeatUserFieldAlreadyExistException() {
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
		restService.loginAdmin().restBuilder().path(UserResource.USERS).path(UserResource.USER_ID).expand(666666002)
				.get().build();
	}

	@Test
	public void testReadUserNotRol() {
		thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
		restService.loginAdmin().restBuilder().path(UserResource.USERS).path(UserResource.USER_ID).expand(666666001)
				.get().build();
	}

	@Test
	public void testReadUserUnauthorized() {
		thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
		restService.logout().restBuilder().path(UserResource.USERS).path(UserResource.USER_ID).expand(666666001).get()
				.build();
	}

	@After
	public void delete() {
		this.restService.loginAdmin();
		restService.restBuilder().path(UserResource.USERS).path(UserResource.USER_ID).expand(this.userDto.getMobile())
				.delete().build();
	}

}
