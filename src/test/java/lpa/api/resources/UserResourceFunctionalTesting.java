package lpa.api.resources;

import static org.junit.Assert.assertEquals;

import lpa.api.dtos.UserProfileDto;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.model.MultipleFailureException;
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
    private UserProfileDto userProfileDto;
    @Autowired
    private UserRepository userRepository;

    @Before
    public void before() {
        this.userDto = new UserDto("666000000");
        this.userProfileDto = new UserProfileDto();
        userProfileDto.setName("Test u007");
        userProfileDto.setDescription("Description User u007");
        userProfileDto.setImage("IMG_URL");
        userProfileDto.setAddress1("address 1");
        userProfileDto.setAddress2("address 2");
        userProfileDto.setCity("New York");
        userProfileDto.setCountry("United States");
        userProfileDto.setMobile("666555888");
        userProfileDto.setZipCode("65200");
        userProfileDto.setLastName("Last from test");
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
    public void testCreateRegisteredUsernameNullException() {
        // MultipleFailureException Se disparan 2 errores: el campo username no puede ser null y tiene que
        // cumplir el patron USERNAME_PATTERN
        thrown.expect(MultipleFailureException.class);
        this.userDto.setUsername(null);
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

	/*@Test
	public void testCreateUnauthorized() {
		thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
		restService.logout().restBuilder().path(UserResource.USERS).body(userDto).post().build();
	}*/

    @Test
    public void testReadUser() {
        restService.loginAdmin().restBuilder().path(UserResource.USERS)
                .path(UserResource.USER).expand(666666002).get().build();
    }

    @Test
    public void testReadUsersByRol() {
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
        user.setActive(false);
        UserDto userDto1 = new UserDto(user);
        restService.loginAdmin().restBuilder().path(UserResource.USERS).body(userDto1).put().build();
    }

    @Test
    public void testReadUserNotRol() {
        thrown.expect(new HttpMatcher(HttpStatus.NOT_FOUND));
        restService.loginAdmin().restBuilder().path(UserResource.USERS)
                .path(UserResource.USER).expand(666666001).get().build();
    }

    /*@Test
	public void testReadUserUnauthorized() {
		thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
		restService.logout().restBuilder().path(UserResource.USERS).path(UserResource.USERUSERNAME)
				.path(UserResource.USERNAME_ID).expand(666666001).get().build();
	}*/

    @Test
    public void testReadCurrentUser() {
        UserMinimumDto userMinimumDto = restService.loginRegistered().restBuilder(new RestBuilder<UserMinimumDto>())
                .clazz(UserMinimumDto.class).path(UserResource.USERS).path(UserResource.CURRENT).get().build();
        assertEquals("666666002", userMinimumDto.getUsername());
    }

    /*User Profile Test*/
    @Test
    public void testCreateUserProfile() {
        UserProfileDto userProfileDto1 = this.restService.loginAdmin().restBuilder(new RestBuilder<UserProfileDto>())
                .path(UserResource.USERS).clazz(UserProfileDto.class).path(UserResource.USER).expand("666666007")
                .path(UserResource.PROFILE).body(userProfileDto).post().build();
        assertEquals("UserProfileDto{name='Test u007', lastName='Last from test', mobile='666555888', address1='address 1', address2='address 2', city='New York', zipCode='65200', country='United States', image='IMG_URL', description='Description User u007'}",
                userProfileDto1.toString());
    }

    @Test
    public void testReadUserProfile() {
        UserProfileDto userProfileDto1 =  restService.loginRegistered().restBuilder(new RestBuilder<UserProfileDto>()).path(UserResource.USERS)
                .clazz(UserProfileDto.class).path(UserResource.USER).expand("666666002")
                .path(UserResource.PROFILE).get().build();
        assertEquals("Chicote 002",userProfileDto1.getLastName());
    }

    @Test
    public void testUpdateUserProfile() {
        userProfileDto.setName("Update u002");
        UserProfileDto userProfileDto1 = this.restService.loginRegistered().restBuilder(new RestBuilder<UserProfileDto>())
                .path(UserResource.USERS).clazz(UserProfileDto.class).path(UserResource.USER).expand("666666002")
                .path(UserResource.PROFILE).body(userProfileDto).put().build();
        assertEquals("Update u002", userProfileDto1.getName());
    }

    @Test
    public void testCreateUserProfileAlreadyExistException() {
        thrown.expect(new HttpMatcher(HttpStatus.BAD_REQUEST));
        UserProfileDto userProfileDto = new UserProfileDto();
        this.restService.loginRegistered().restBuilder(new RestBuilder<UserProfileDto>())
                .path(UserResource.USERS).clazz(UserProfileDto.class).path(UserResource.USER).expand("666666004")
                .path(UserResource.PROFILE).body(userProfileDto).post().build();
    }

    @Test
    public void testUpdateUserProfileForbiddenException() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        UserProfileDto userProfileDto = new UserProfileDto();
        this.restService.logout().restBuilder().path(UserResource.USERS).path(UserResource.USER).expand("666666002")
                .path(UserResource.PROFILE).body(userProfileDto).put().build();
    }

    @Test
    public void testUpdateUserProfileRegisteredNotOwnerForbiddenException() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        UserProfileDto userProfileDto = new UserProfileDto();
        this.restService.loginRegistered().restBuilder().path(UserResource.USERS).path(UserResource.USER).expand("666666003")
                .path(UserResource.PROFILE).body(userProfileDto).put().build();
    }

    @Test
    public void testUpdateUserProfileNotRegisteredForbiddenException() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        UserProfileDto userProfileDto = new UserProfileDto();
        this.restService.logout().restBuilder().path(UserResource.USERS).path(UserResource.USER).expand("666666003")
                .path(UserResource.PROFILE).body(userProfileDto).put().build();
    }

    @Test
    public void testDeleteUserProfile() {
        this.restService.loginAdmin().restBuilder().path(UserResource.USERS).path(UserResource.USER).expand("666666002")
                .path(UserResource.PROFILE).delete().build();
    }

    @Test
    public void testDeleteUserProfileNoAdminForbiddenException() {
        thrown.expect(new HttpMatcher(HttpStatus.FORBIDDEN));
        this.restService.loginRegistered().restBuilder().path(UserResource.USERS).path(UserResource.USER).expand("666666002")
                .path(UserResource.PROFILE).delete().build();
    }

    @After
    public void delete() {
        this.restService.loginAdmin();
        restService.restBuilder().path(UserResource.USERS).path(UserResource.USER)
                .expand(this.userDto.getUsername()).delete().build();
    }

}
