package lpa.api.resources.exceptions;

public class PetTypeException extends Exception {

	private static final long serialVersionUID = -1303867838778094715L;

	public static final String DESCRIPTION = "Pet Type Exception";

	public PetTypeException() {
		super(DESCRIPTION);
	}

	public PetTypeException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
