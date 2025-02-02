package app.peluargo.user.api.commons.dtos;

import java.time.Instant;

public record ApiResponseErrorDTO(
        String type,
        String description,
        Instant timestamp
) {
    public ApiResponseErrorDTO(RuntimeException exception) {
        this(
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Instant.now()
        );
    }

    public ApiResponseErrorDTO(Exception exception) {
        this(
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Instant.now()
        );
    }
}
