package es.jaac.petlost;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import es.jaac.petlost.api.daos.DaoFactory;
import es.jaac.petlost.api.daos.firebase.DaoFirebaseFactory;
import es.jaac.petlost.api.resources.CaptGeosResource;
import es.jaac.petlost.api.resources.UsersResource;
import es.jaac.petlost.http.HttpClientService;
import es.jaac.petlost.http.HttpMethod;
import es.jaac.petlost.http.HttpRequest;
import es.jaac.petlost.http.HttpRequestBuilder;

public class CaptGeosResourceFunctionalTesting {

	@Before
	public void before() {
		DaoFactory.setFactory(new DaoFirebaseFactory());
	}

	private void createUser(String name) {
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(UsersResource.USERS).body(name)
				.build();
		new HttpClientService().httpRequest(request);
	}

	@Test
	public void testCaptgeosDelete() {
		// Create user
		this.createUser("jaac");
		// Add CaptGeos 1
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(CaptGeosResource.ADDCAPTGEO)
				.path(UsersResource.ID).expandPath("1").body("lostpetinNY:images/img.jpg:5489496:5151558").build();
		new HttpClientService().httpRequest(request);
		// // Add CaptGeos 2
		// request = new
		// HttpRequestBuilder().method(HttpMethod.POST).path(CaptGeosResource.ADDCAPTGEO)
		// .path(UsersResource.ID).expandPath("1").body("lostpetinSanFrancisco:images/img.jpg:5489496:5151558")
		// .build();
		new HttpClientService().httpRequest(request);
		// Delete CaptGeos
		// request = new
		// HttpRequestBuilder().method(HttpMethod.DELETE).path(CaptGeosResource.DELTECAPTGEO)
		// .path(UsersResource.ID).expandPath("1").build();
		// new HttpClientService().httpRequest(request);
		// Get CaptGeos
		request = new HttpRequestBuilder().method(HttpMethod.GET).path(UsersResource.USERS).path(UsersResource.ID)
				.expandPath("1").build();
		assertEquals(
				"[{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}]",
				new HttpClientService().httpRequest(request).getBody());
	}
}
