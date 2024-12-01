package app.peluargo.user.api.dtos;

import java.time.LocalDate;

public record UserCreationDTO(
        String firstName,
        String lastName,
        LocalDate birthdate,
        String email
) {
}
