package com.example.v2CreditAppConcept.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class RegistrationException extends Exception{
    public RegistrationException(String message) {
        super(message);
    }
}
