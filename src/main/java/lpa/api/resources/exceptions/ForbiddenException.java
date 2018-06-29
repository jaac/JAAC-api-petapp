package lpa.api.resources.exceptions;

public class ForbiddenException extends Exception {
    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Forbidden. Insufficient role";

    public ForbiddenException() {
        super(DESCRIPTION);
    }

    public ForbiddenException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
