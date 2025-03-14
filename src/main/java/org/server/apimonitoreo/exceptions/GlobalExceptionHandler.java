package org.server.apimonitoreo.exceptions;

import org.server.apimonitoreo.models.dtos.Errors.ErrorDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(EntityNotFount.class)
    public ResponseEntity<?> entityNotFound(EntityNotFount e, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(
                HttpStatus.NOT_FOUND.value(),
                e.getMessage(),
                request.getDescription(false)
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }
}
