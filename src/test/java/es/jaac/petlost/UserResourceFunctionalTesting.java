package es.jaac.petlost;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.jaac.petlost.api.Dispatcher;
import es.jaac.petlost.api.daos.DaoFactory;
import es.jaac.petlost.api.daos.firebase.DaoFirebaseFactory;
import es.jaac.petlost.http.HttpClientService;
import es.jaac.petlost.http.HttpException;
import es.jaac.petlost.http.HttpMethod;
import es.jaac.petlost.http.HttpRequest;
import es.jaac.petlost.http.HttpRequestBuilder;

public class UserResourceFunctionalTesting {

	@Before
	public void before() {
		DaoFactory.setFactory(new DaoFirebaseFactory());
	}

	private void createUser() {
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(Dispatcher.USERS).body("jaac")
				.build();
		new HttpClientService().httpRequest(request);
	}

	@Test
	public void testCreateUser() {
		this.createUser();
	}

	@Test(expected = HttpException.class)
	public void testCreateNameUserEmpty() {
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(Dispatcher.USERS).body("").build();
		new HttpClientService().httpRequest(request);
	}

	@Test(expected = HttpException.class)
	public void testCreateUserEmpty() {
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(Dispatcher.USERS).build();
		new HttpClientService().httpRequest(request);
	}

	@Test
	public void testReadUser() {
		this.createUser();
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.USERS).path(Dispatcher.ID)
				.expandPath("1").build();

		assertEquals("{\"id\":1,\"name\":\"jaac\",\"captgeos\":[]}",
				new HttpClientService().httpRequest(request).getBody());
	}

	@Test
	public void testUserList() {
		this.createUser();
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.USERS).build();
		assertEquals("[{\"id\":1,\"name\":\"jaac\",\"captgeos\":[]}]",
				new HttpClientService().httpRequest(request).getBody());
	}

	@Test
	public void testEmptyUserList() {
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.ALLUSERS).build();
		assertEquals("[]", new HttpClientService().httpRequest(request).getBody());
	}

	@Test
	public void testCaptgeosList() {
		this.createUser();

		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(Dispatcher.ADDCAPTGEO)
				.path(Dispatcher.ID).expandPath("1").body("lostpetinNY:images/img.jpg:5489496:5151558").build();
		new HttpClientService().httpRequest(request);

		request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.USERCAPTGEO).build();
		assertEquals(
				"[{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}]",
				new HttpClientService().httpRequest(request).getBody());
	}

	@Test
	public void testUserAddCaptgeos() {
		this.createUser();
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(Dispatcher.ADDCAPTGEO)
				.path(Dispatcher.ID).expandPath("1").body("lostpetinNY:images/img.jpg:5489496:5151558").build();
		new HttpClientService().httpRequest(request);

		request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.USERCAPTGEO).path(Dispatcher.ID)
				.expandPath("1").build();
		assertEquals(
				"[{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}]",
				new HttpClientService().httpRequest(request).getBody());

	}

}
