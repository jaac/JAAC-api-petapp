package lpa.api.resources.exceptions;

public class LostPetIdNotFoundException extends Exception {

	private static final long serialVersionUID = -604725764267225206L;
	public static final String DESCRIPTION = "Lost Pet id not found";

	public LostPetIdNotFoundException() {
		super(DESCRIPTION);
	}

	public LostPetIdNotFoundException(String detail) {
		super(DESCRIPTION + "." + detail);
	}
}
