package lpa.api.resources.exceptions;

public class PetTypeAlreadyExistException extends Exception {

	private static final long serialVersionUID = 9137200882882144027L;
	public static final String DESCRIPTION = "PetType Already Exist";

	public PetTypeAlreadyExistException() {
		super(DESCRIPTION);
	}

	public PetTypeAlreadyExistException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
