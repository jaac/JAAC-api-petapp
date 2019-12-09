package lpa.api.resources.exceptions;

public class UserFieldErrorException extends Exception {
    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "User field Error";

    public UserFieldErrorException() {
        super(DESCRIPTION);
    }

    public UserFieldErrorException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
