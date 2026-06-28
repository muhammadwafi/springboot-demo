package com.spring.demo.app.auth.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class CurrentPasswordIncorrectException extends RuntimeException {

    public CurrentPasswordIncorrectException() {
        super("Current passsword is incorrect");
    }

    public CurrentPasswordIncorrectException(String message) {
        super(message);
    }
}
