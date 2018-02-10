package es.jaac.petlost;

import static org.junit.Assert.assertEquals;

//import org.junit.Before;
import org.junit.Test;

import es.jaac.petlost.api.Dispatcher;
import es.jaac.petlost.http.HttpClientService;
import es.jaac.petlost.http.HttpException;
import es.jaac.petlost.http.HttpMethod;
import es.jaac.petlost.http.HttpRequest;
import es.jaac.petlost.http.HttpRequestBuilder;

public class UserResourceFunctionalTesting {
	
//	@Before
//	public void before() {
//		DaoFactory.setFactory(new DaoFactoryFirebase());
//	}
	
	private void createUser() {
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(Dispatcher.USERS).body("1:jaac:1:lostpetinNY:images/img.jpg:5489496:5151558").build();
		new HttpClientService().httpRequest(request);
	}
	
	private void createUserCG() {
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(Dispatcher.USERS).body("1:jaac:1:lostpetinNY:images/img.jpg:5489496:5151558").build();
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
	public void testCreateUserEmty() {
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(Dispatcher.USERS).build();
		new HttpClientService().httpRequest(request);
	}
	
	@Test
	public void testReadUser() {
		this.createUser();
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.USERS).path(Dispatcher.ID).expandPath("1").build();
		
		assertEquals("{\"id\":1,\"name\":\"jaac\",\"captgeos\":[{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}]}",new HttpClientService().httpRequest(request).getBody());
	}
	
	@Test 
	public void testUserList() {
	this.createUser();
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.USERS).build();		
		assertEquals("[{\"id\":1,\"name\":\"jaac\",\"captgeos\":null}]",new HttpClientService().httpRequest(request).getBody());
	}
	
	@Test 
	public void testEmptyUserList() {
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.ALLUSERS).build();	
		assertEquals("",new HttpClientService().httpRequest(request).getBody());
	}
	
	@Test
	public void testReadUserWhitCaptgeos() {
		this.createUserCG();
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.USERS).path(Dispatcher.ID).expandPath("1").build();		
		assertEquals("{\"id\":1,\"name\":\"jaac\",\"captgeos\":[{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}]}",new HttpClientService().httpRequest(request).getBody());
	}
	
	@Test
	public void testUserAddCaptgeos() {
		this.createUser();
		
		HttpRequest request = new HttpRequestBuilder().method(HttpMethod.POST).path(Dispatcher.USERS).path(Dispatcher.ID).expandPath("1").path(Dispatcher.ADDCAPTGEO).body("1:lostpetinNY:images/img.jpg:5489496:5151558").build();
		new HttpClientService().httpRequest(request);
		
		request = new HttpRequestBuilder().method(HttpMethod.GET).path(Dispatcher.USERS).path(Dispatcher.ID).expandPath("1").path(Dispatcher.USERCAPTGEO).build();		
		assertEquals("{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}",new HttpClientService().httpRequest(request).getBody());
	
	}

}
