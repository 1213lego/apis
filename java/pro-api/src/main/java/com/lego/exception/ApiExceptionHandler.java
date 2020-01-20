package com.lego.exception;

import com.google.common.base.Strings;
import com.lego.exception.resourceExceptions.ResourceAlreadyExistsException;
import com.lego.exception.resourceExceptions.ResourceException;
import com.lego.exception.resourceExceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class ApiExceptionHandler {
    private static final String BAD_REQUEST_RESOURCE_MESSAGE = "El recurso %s no se ecuentra bien formado";

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionResponse> handleValidationExceptions(MethodArgumentNotValidException e) {
        Map<String, String> errors = e.getBindingResult().getFieldErrors()
                .stream()
                .collect(Collectors.toMap(FieldError::getField,
                        fieldError -> Strings.nullToEmpty(fieldError.getDefaultMessage())));
        HttpStatus conflict = HttpStatus.BAD_REQUEST;
        String resource = e.getParameter().getParameterType().getSimpleName();
        String message = String.format(BAD_REQUEST_RESOURCE_MESSAGE, resource);
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                conflict,
                message,
                errors,
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponse, conflict);
    }

   /* @ExceptionHandler(value = {ResourceAlreadyExistsException.class})
    public ResponseEntity<ExceptionResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e) {
        HttpStatus conflict = HttpStatus.CONFLICT;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                conflict,
                e.getDetailMessage(),
                e.getLocalizedMessage(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponse, conflict);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        HttpStatus conflict = HttpStatus.NOT_FOUND;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                conflict,
                e.getMessage(),
                e.getCause(),
                LocalDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponse, conflict);
    }*/
}
