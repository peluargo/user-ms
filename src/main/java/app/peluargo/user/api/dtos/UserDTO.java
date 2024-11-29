package app.peluargo.user.api.dtos;

import java.util.Date;
import java.util.UUID;

public record UserDTO(
        UUID id,
        String firstName,
        String lastName,
        Date bithdate,
        String email
) {
}
