package es.jaac.petlost.api.resources.exceptions;

public class UserIdNotFoundExeception extends Exception {

	private static final long serialVersionUID = 1425873630814710432L;
	public static final String DESCRIPTION ="Id de usuario no encontrado";
	
	public UserIdNotFoundExeception(String detail){
		super(DESCRIPTION + "" + detail);
	}
	
	public UserIdNotFoundExeception(){
		this("");
	}

}
