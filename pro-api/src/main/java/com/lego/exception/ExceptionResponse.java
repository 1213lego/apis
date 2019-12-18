package com.lego.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import java.time.ZonedDateTime;
@Data
public class ExceptionResponse {
    private final String message;
    private final Throwable throwable;
    private final HttpStatus httpStatus;
    private final ZonedDateTime time;
}
