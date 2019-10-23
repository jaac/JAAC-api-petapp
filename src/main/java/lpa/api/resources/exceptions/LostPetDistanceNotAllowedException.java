package lpa.api.resources.exceptions;

public class LostPetDistanceNotAllowedException extends Exception {

	private static final long serialVersionUID = -5442903654105098020L;
	public static final String DESCRIPTION = "Distance no allowed";

	public LostPetDistanceNotAllowedException() {
		super(DESCRIPTION);
	}

	public LostPetDistanceNotAllowedException(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
