package app.peluargo.user.api.dtos;

import java.time.LocalDate;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email
) {
}
