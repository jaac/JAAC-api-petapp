package lpa.api.resources.exceptions;

public class LostPetBadRequest extends Exception {

	private static final long serialVersionUID = 5201482641747772315L;

	public static final String DESCRIPTION = "Bad algo";

	public LostPetBadRequest() {
		super(DESCRIPTION);
	}

	public LostPetBadRequest(String detail) {
		super(DESCRIPTION + ". " + detail);
	}
}