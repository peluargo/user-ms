package app.peluargo.user.api.exceptions;

public class UserEmailIsNotAvailableException extends InvalidUserEmailException {
    public UserEmailIsNotAvailableException() {
        super("Email is not available");
    }
}

