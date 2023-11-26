package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.entities.dto.BorrowerRegistrationDto;
import com.example.v2CreditAppConcept.exceptions.RegistrationException;
import com.example.v2CreditAppConcept.services.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowerRegistrationController {

    private BorrowerService borrowerService;


    public BorrowerRegistrationController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping("/registration/borrower")
    private String registration(@RequestBody @Valid BorrowerRegistrationDto borrowerRegistrationDto)throws RegistrationException {

        this.borrowerService.registration(borrowerRegistrationDto);

        return "Borrower is registered successfully";
    }
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleException(RegistrationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid credentials");
    }
}
