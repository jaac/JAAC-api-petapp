package es.jaac.petlost.api;

import es.jaac.petlost.api.resources.CaptGeosResource;
import es.jaac.petlost.api.resources.UsersResource;
import es.jaac.petlost.http.HttpException;
import es.jaac.petlost.http.HttpRequest;
import es.jaac.petlost.http.HttpResponse;
import es.jaac.petlost.http.HttpStatus;

public class Dispatcher {

	public static final String ID = "/{id}";
	public static final String USERS = "users";
	public static final String USERCAPTGEO = "userpatgeos";
	public static final String ADDCAPTGEO = "addcaptgeo";
	public static final String ALLUSERS = "allusers";

	private UsersResource usersResource = new UsersResource();
	private CaptGeosResource captGeoResource = new CaptGeosResource();

	private void responseError(HttpResponse response, Exception e) {
		response.setBody("{\"error\":\"" + e + "\"}");
		response.setStatus(HttpStatus.BAD_REQUEST);
	}

	public void doPost(HttpRequest request, HttpResponse response) {
		try {

			if (request.isEqualsPath(UsersResource.USERS)) {

				String name = request.getBody();

				usersResource.createUser(name);

				if (request.getBody() == "") {
					responseError(response, new HttpException(request.getPath()));
				} else if (request.getBody() == null) {
					responseError(response, new HttpException(request.getPath()));
				} else {
					response.setStatus(HttpStatus.CREATED);
				}

			} else if (request.isEqualsPath(CaptGeosResource.ADDCAPTGEO + CaptGeosResource.ID)) {
				int userid = Integer.valueOf(request.paths()[1]);
				String name = request.getBody().split(":")[0];
				String img = request.getBody().split(":")[1];
				int lat = Integer.valueOf(request.getBody().split(":")[2]);
				int longi = Integer.valueOf(request.getBody().split(":")[3]);
				captGeoResource.addCaptGeo(userid, name, img, lat, longi);
				response.setStatus(HttpStatus.CREATED);
			}

		} catch (Exception e) {
			responseError(response, e);
		}

	}

	public void doGet(HttpRequest request, HttpResponse response) {
		try {
			if (request.isEqualsPath(UsersResource.USERS + UsersResource.ID)) {
				response.setBody(usersResource.readUser(Integer.valueOf(request.paths()[1])).toString());

				// if(Integer.valueOf(request.paths()[1]) == 2) {
				// response.setBody("{\"id\":2,\"name\":\"jaac\",\"captgeos\":[{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}]}");
				// }else if(Integer.valueOf(request.paths()[1]) == 1) {
				// response.setBody("{\"id\":1,\"name\":\"jaac\",\"captgeos\":null}");
				// }else {
				// response.setBody("{\"id\":33,\"name\":\"jaac\",\"captgeos\":null}");
				// }
			} else if (request.isEqualsPath(UsersResource.USERS)) {
				response.setBody(usersResource.userList().toString());
			} else if (request.isEqualsPath(Dispatcher.ALLUSERS)) {
				response.setBody(usersResource.userList().toString());
				// response.setBody("[{\"id\":1,\"name\":\"jaac\",\"captgeos\":null}]");
			} else if (request.isEqualsPath(CaptGeosResource.USERCAPTGEO + UsersResource.ID )) {
				response.setBody(captGeoResource.getCaptGeosUser(Integer.valueOf(request.paths()[1])).toString());
				/// response.setBody("{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}");
			}else if (request.isEqualsPath(CaptGeosResource.USERCAPTGEO)) {
				response.setBody(captGeoResource.getCaptGeosList().toString());
				/// response.setBody("{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}");
			}
		} catch (Exception e) {
			responseError(response, e);
		}
	}

	public void doPut(HttpRequest request, HttpResponse response) {
		// TODO Auto-generated method stub

	}

	public void doPatch(HttpRequest request, HttpResponse response) {
		// TODO Auto-generated method stub

	}

	public void doDelete(HttpRequest request, HttpResponse response) {
		// TODO Auto-generated method stub

	}

}
