package app.peluargo.user.api.advices;

import app.peluargo.user.api.commons.dtos.ApiResponseDTO;
import app.peluargo.user.api.commons.dtos.ApiResponseErrorDTO;
import app.peluargo.user.api.commons.mappers.ApiResponseMapper;
import app.peluargo.user.api.exceptions.UserEmailIsNotAvailableException;
import app.peluargo.user.api.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class UserControllerAdvice {
    @ExceptionHandler({ UserEmailIsNotAvailableException.class, MissingPathVariableException.class })
    public ResponseEntity<ApiResponseDTO<Void>> badRequest(Exception exception) {
        List<ApiResponseErrorDTO> errors = new ArrayList<>();
        errors.add(new ApiResponseErrorDTO(exception));
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApiResponseMapper.toApiResponse(errors));
    }

    @ExceptionHandler({ UserNotFoundException.class })
    public ResponseEntity<ApiResponseDTO<Void>> notFound(Exception exception) {
        List<ApiResponseErrorDTO> errors = new ArrayList<>();
        errors.add(new ApiResponseErrorDTO(exception));
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiResponseMapper.toApiResponse(errors));
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ApiResponseDTO<Void>> internalError(Exception exception) {
        List<ApiResponseErrorDTO> errors = new ArrayList<>();
        errors.add(new ApiResponseErrorDTO(exception));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResponseMapper.toApiResponse(errors));
    }
}
