package app.peluargo.user.api.commons.dtos;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public record ApiResponseDTO<T>(
        Instant timestamp,
        List<ApiResponseErrorDTO> errors,
        Optional<T> content
        ) {
    public ApiResponseDTO(Instant timestamp, List<ApiResponseErrorDTO> errors) {
        this(timestamp, errors, Optional.empty());
    }
}
