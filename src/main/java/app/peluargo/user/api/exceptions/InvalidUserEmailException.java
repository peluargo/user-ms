package app.peluargo.user.api.exceptions;

public class InvalidUserEmailException extends RuntimeException {
    public InvalidUserEmailException(String message) {
        super(message);
    }
}

