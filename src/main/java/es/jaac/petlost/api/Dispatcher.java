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
	public static final String USERCAPTGEO = "/userpatgeos";
	public static final String ADDCAPTGEO = "/addcaptgeo";
	public static final String ALLUSERS = "allusers";
	
	private UsersResource usersResource = new UsersResource();
	private CaptGeosResource captgeoResource = new CaptGeosResource();

    private void responseError(HttpResponse response, Exception e) {
        response.setBody("{\"error\":\"" + e + "\"}");
        response.setStatus(HttpStatus.BAD_REQUEST);
    }

	public void doPost(HttpRequest request, HttpResponse response) {
		try {
			
			if(request.isEqualsPath(UsersResource.USERS)) {
				int id  = Integer.valueOf(request.getBody().split(":")[0]);
				String name = request.getBody().split(":")[1];
				String cgid = request.getBody().split(":")[2];
				String cgname = request.getBody().split(":")[3];
				String cgimg = request.getBody().split(":")[4];
				String cglat = request.getBody().split(":")[5];
				String cglong = request.getBody().split(":")[6];
				usersResource.createUser(id ,name, Integer.valueOf(cgid), cgname, cgimg, Integer.valueOf(cglat), Integer.valueOf(cglong));
				
				if(request.getBody() == "") {
					responseError(response, new HttpException(request.getPath()));
				}else if(request.getBody() == null) {
					responseError(response, new HttpException(request.getPath()));
				}
				else {
					response.setStatus(HttpStatus.CREATED);
				}
				
			}else if(request.isEqualsPath(UsersResource.USERS + UsersResource.ID + CaptGeosResource.ADDCAPTGEO)) {
				
				captgeoResource.addCaptGeo(Integer.valueOf(request.paths()[1]));
				response.setStatus(HttpStatus.CREATED);
			}
			
		}catch(Exception e){
			responseError(response,e);
		}
		
	}

	public void doGet(HttpRequest request, HttpResponse response) {
		try {
			if(request.isEqualsPath(UsersResource.USERS + UsersResource.ID)) {
				response.setBody(usersResource.readUser(request.paths()[1]).toString());
				
//				if(Integer.valueOf(request.paths()[1]) ==  2) {
//					response.setBody("{\"id\":2,\"name\":\"jaac\",\"captgeos\":[{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}]}");
//				}else if(Integer.valueOf(request.paths()[1]) ==  1) {
//					response.setBody("{\"id\":1,\"name\":\"jaac\",\"captgeos\":null}");
//				}else {
//					response.setBody("{\"id\":33,\"name\":\"jaac\",\"captgeos\":null}");
//				}
			}else if(request.isEqualsPath(UsersResource.USERS)) {
				response.setBody(usersResource.userList().toString());
			}else if(request.isEqualsPath(UsersResource.ALLUSERS)) {
				response.setBody(usersResource.userListEm().toString());
//				response.setBody("[{\"id\":1,\"name\":\"jaac\",\"captgeos\":null}]");
			}else if(request.isEqualsPath(UsersResource.USERS + UsersResource.ID + CaptGeosResource.USERCAPTGEO)) {
				response.setBody(captgeoResource.getCaptGeosUser(Integer.valueOf(request.paths()[1])).toString());
				///response.setBody("{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}");
			}
		}catch(Exception e){
			responseError(response,e);
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
