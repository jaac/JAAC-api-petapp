package lpa.api.resources.exceptions;

public class LostPetException extends Exception {

	private static final long serialVersionUID = 4247262790796116045L;
	public static final String DESCRIPTION = "Lost Pet Exception";

	public LostPetException() {
		super(DESCRIPTION);
	}

	public LostPetException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
