package app.peluargo.user.api;

import app.peluargo.user.api.dtos.ApiErrorResponseDTO;
import app.peluargo.user.api.exceptions.InvalidUserEmailException;
import app.peluargo.user.api.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler({
            InvalidUserEmailException.class
    })
    public ResponseEntity<ApiErrorResponseDTO> badRequest(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponseDTO(exception));
    }

    @ExceptionHandler({
            MissingPathVariableException.class
    })
    public ResponseEntity<ApiErrorResponseDTO> badRequest(Exception exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApiErrorResponseDTO(exception));
    }

    @ExceptionHandler({
            UserNotFoundException.class
    })
    public ResponseEntity<ApiErrorResponseDTO> notFound(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiErrorResponseDTO(exception));
    }

    @ExceptionHandler({ Exception.class })
    public ResponseEntity<ApiErrorResponseDTO> internalError(Exception exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiErrorResponseDTO(exception));
    }

}
