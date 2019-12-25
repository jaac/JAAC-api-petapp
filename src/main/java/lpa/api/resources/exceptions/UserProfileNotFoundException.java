package lpa.api.resources.exceptions;

public class UserProfileNotFoundException extends Exception {
    private static final long serialVersionUID = -1344640670884805385L;

    public static final String DESCRIPTION = "Profile not found";

    public UserProfileNotFoundException() {
        super(DESCRIPTION);
    }

    public UserProfileNotFoundException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
