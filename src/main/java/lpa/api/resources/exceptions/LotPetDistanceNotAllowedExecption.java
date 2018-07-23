package lpa.api.resources.exceptions;

public class LotPetDistanceNotAllowedExecption extends Exception {

	private static final long serialVersionUID = -5442903654105098020L;
	public static final String DESCRIPTION = "Distance no allowed";

	public LotPetDistanceNotAllowedExecption() {
		super(DESCRIPTION);
	}

	public LotPetDistanceNotAllowedExecption(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}
