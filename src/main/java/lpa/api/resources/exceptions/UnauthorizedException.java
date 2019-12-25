package lpa.api.resources.exceptions;

public class UnauthorizedException extends Exception{
    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Unauthorized. Not allowed";

    public UnauthorizedException() {
        super(DESCRIPTION);
    }

    public UnauthorizedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
