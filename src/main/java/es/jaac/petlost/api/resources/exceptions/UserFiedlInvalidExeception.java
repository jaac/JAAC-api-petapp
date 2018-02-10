package es.jaac.petlost.api.resources.exceptions;

public class UserFiedlInvalidExeception extends Exception {

	private static final long serialVersionUID = -6434335960305730794L;
	public static final String DESCRIPTION ="Campo de Usuario invalido";
	
	public UserFiedlInvalidExeception(String detail){
		super(DESCRIPTION + "" + detail);
	}
	
	public UserFiedlInvalidExeception(){
		this("");
	}

}
