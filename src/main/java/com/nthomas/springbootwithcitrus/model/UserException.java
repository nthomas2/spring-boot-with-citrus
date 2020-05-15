package com.nthomas.springbootwithcitrus.model;

import org.apache.commons.lang3.ObjectUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class UserException extends ResponseStatusException {
    public UserException(String message) {
        this(null, message, null);
    }

    public UserException(String message, Throwable throwable) {
        this(null, message, throwable);
    }

    public UserException(HttpStatus status, String message) {
        this(status, message, null);
    }

    public UserException(HttpStatus status, String message, Throwable throwable) {
        super(ObjectUtils.defaultIfNull(status, HttpStatus.BAD_REQUEST), message, throwable);
    }
}
