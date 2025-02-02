package app.peluargo.user.api.exceptions;

public class UserEmailIsNotAvailableException extends RuntimeException {
    public UserEmailIsNotAvailableException(String email) {
        super(String.format("Email '%s' is not available", email));
    }
}

