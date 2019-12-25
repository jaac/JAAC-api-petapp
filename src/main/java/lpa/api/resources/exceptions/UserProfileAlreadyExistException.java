package lpa.api.resources.exceptions;

public class UserProfileAlreadyExistException extends Exception {
    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Profile already exist";

    public UserProfileAlreadyExistException() {
        super(DESCRIPTION);
    }

    public UserProfileAlreadyExistException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
