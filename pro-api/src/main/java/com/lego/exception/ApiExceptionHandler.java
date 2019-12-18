package com.lego.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = {ResourceAlreadyExistsException.class})
    public ResponseEntity<?> handleResourceAlreadyExistsException(ResourceAlreadyExistsException e){
        HttpStatus conflict = HttpStatus.CONFLICT;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                e,
                conflict,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponse,conflict);
    }

    @ExceptionHandler(value = {ResourceNotFoundException.class})
    public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
        HttpStatus conflict = HttpStatus.NOT_FOUND;
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                e.getMessage(),
                e,
                conflict,
                ZonedDateTime.now()
        );
        return new ResponseEntity<>(exceptionResponse,conflict);
    }
}
