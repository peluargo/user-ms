package app.peluargo.user.api.exceptions;

import java.util.UUID;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(UUID id) {
        super(String.format("User with id '%s' not found", id));
    }
}

