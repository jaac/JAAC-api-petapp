package lpa.api.resources.exceptions;

public class DeleteNotAllowedException extends Exception {

	private static final long serialVersionUID = 9034734215989918619L;
	public static final String DESCRIPTION = "Invalid Field";

	public DeleteNotAllowedException() {
		super(DESCRIPTION);
	}

	public DeleteNotAllowedException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
