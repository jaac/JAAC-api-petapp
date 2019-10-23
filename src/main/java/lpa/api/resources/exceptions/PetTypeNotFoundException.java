package lpa.api.resources.exceptions;

public class PetTypeNotFoundException extends Exception {

	private static final long serialVersionUID = -4921610023481526296L;
	public static final String DESCRIPTION = "Pet Type Not Found";

	public PetTypeNotFoundException() {
		super(DESCRIPTION);
	}

	public PetTypeNotFoundException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
