package app.peluargo.user.api.commons.mappers;

import app.peluargo.user.api.commons.dtos.ApiResponseDTO;
import app.peluargo.user.api.commons.dtos.ApiResponseErrorDTO;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ApiResponseMapper {
    public static <T> ApiResponseDTO<T> toApiResponse(T content) {
        return new ApiResponseDTO<>(
                Instant.now(),
                new ArrayList<>(),
                Optional.of(content)
        );
    }
    public static <T> ApiResponseDTO<T> toApiResponse(List<ApiResponseErrorDTO> errors) {
        return new ApiResponseDTO<>(
                Instant.now(),
                errors
        );
    }


    public static ApiResponseErrorDTO toApiResponseErrorDTO(Exception exception) {
        return new ApiResponseErrorDTO(
                exception.getClass().getSimpleName(),
                exception.getMessage(),
                Instant.now()
        );
    }
}
