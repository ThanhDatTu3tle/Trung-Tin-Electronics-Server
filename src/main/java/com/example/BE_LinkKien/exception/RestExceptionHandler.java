package com.example.BE_LinkKien.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestExceptionHandler {

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }

    @ExceptionHandler(CustomException.class)
    private ResponseEntity<Object> handleCustomException(CustomException ex) {
        return buildResponseEntity(new ApiError(ex.getHttpStatus(), ex.getMessage(), ex));
    }

    @ExceptionHandler(AccessDeniedException.class)
    private ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex) {
        return buildResponseEntity(new ApiError(HttpStatus.BAD_GATEWAY,ex.getMessage(), ex));
    }
}
