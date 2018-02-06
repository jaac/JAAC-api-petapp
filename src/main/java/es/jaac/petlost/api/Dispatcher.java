package es.jaac.petlost.api;

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

    private void responseError(HttpResponse response, Exception e) {
        response.setBody("{\"error\":\"" + e + "\"}");
        response.setStatus(HttpStatus.BAD_REQUEST);
    }

	public void doPost(HttpRequest request, HttpResponse response) {
		try {
			
			if(request.isEqualsPath(USERS)) {
				if(request.getBody() == "") {
					responseError(response, new HttpException(request.getPath()));
				}else if(request.getBody() == null) {
					responseError(response, new HttpException(request.getPath()));
				}
				else {
					response.setStatus(HttpStatus.CREATED);
				}
				
			}else if(request.isEqualsPath(USERS + ID + ADDCAPTGEO)) {
				response.setStatus(HttpStatus.CREATED);
			}
			
		}catch(Exception e){
			responseError(response,e);
		}
		
	}

	public void doGet(HttpRequest request, HttpResponse response) {
		try {
			if(request.isEqualsPath(USERS + ID)) {
				if(Integer.valueOf(request.paths()[1]) ==  2) {
					response.setBody("{\"id\":2,\"name\":\"jaac\",\"captgeos\":[{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}]}");
				}else if(Integer.valueOf(request.paths()[1]) ==  1) {
					response.setBody("{\"id\":1,\"name\":\"jaac\",\"captgeos\":null}");
				}else {
					response.setBody("{\"id\":33,\"name\":\"jaac\",\"captgeos\":null}");
				}
				
			}else if(request.isEqualsPath(USERS)) {
				response.setBody("");
			}else if(request.isEqualsPath(ALLUSERS)) {
				response.setBody("[{\"id\":1,\"name\":\"jaac\",\"captgeos\":null}]");
			}else if(request.isEqualsPath(USERS + ID + USERCAPTGEO)) {
				response.setBody("{\"id\":1,\"name\":\"lostpetinNY\",\"img\":\"images/img.jpg\",\"lat\":5489496,\"long\":5151558}");
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
