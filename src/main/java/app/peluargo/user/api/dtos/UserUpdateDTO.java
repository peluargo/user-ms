package app.peluargo.user.api.dtos;

import java.util.Date;

public record UserUpdateDTO(
        String firstName,
        String lastName,
        Date bithdate,
        String email
) {
}
