package com.gama.projeto.bluebank.exceptions.handlers;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

import java.io.IOException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ConstraintViolationException.class, javax.validation.ConstraintViolationException.class})
    public void handleConstraintViolationException(ConstraintViolationException exception,
                                                   ServletWebRequest webRequest) throws IOException {
        assert webRequest.getResponse() != null;
        webRequest.getResponse().sendError(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
