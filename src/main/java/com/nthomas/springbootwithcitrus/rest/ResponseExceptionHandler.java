package com.nthomas.springbootwithcitrus.rest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

@ControllerAdvice
@Slf4j
public class ResponseExceptionHandler {
    @Data
    @AllArgsConstructor
    private static class ExceptionModel {
        private String reason;
    }

    // Default handler appears to be suppressing reason and stacktrace now?
    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<ExceptionModel> handleResponseStatusException(ResponseStatusException exception) {
        log.error("Handled exception", exception);
        return new ResponseEntity<>(new ExceptionModel(exception.getReason()), exception.getStatus());
    }
}
