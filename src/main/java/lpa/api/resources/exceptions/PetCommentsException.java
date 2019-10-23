package lpa.api.resources.exceptions;

public class PetCommentsException extends Exception {

	private static final long serialVersionUID = -4665599170925809488L;
	public static final String DESCRIPTION = "Pet comment error";

	public PetCommentsException() {
		super(DESCRIPTION);
	}

	public PetCommentsException(String detail) {
		super(DESCRIPTION + "." + detail);
	}
}
