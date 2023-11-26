package com.example.v2CreditAppConcept.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class BorrowerLoginException extends Exception {

    public BorrowerLoginException(String message) {
        super(message);
    }
}
