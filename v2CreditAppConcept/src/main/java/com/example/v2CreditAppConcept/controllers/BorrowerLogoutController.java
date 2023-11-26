package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.entities.dto.BorrowerLoginDto;
import com.example.v2CreditAppConcept.entities.dto.BorrowerLogoutDto;
import com.example.v2CreditAppConcept.exceptions.RegistrationException;
import com.example.v2CreditAppConcept.services.BorrowerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowerLogoutController {

    private BorrowerService borrowerService;

    @Autowired
    public BorrowerLogoutController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping("/borrower/logout")
    private String logout(@RequestBody BorrowerLogoutDto borrowerLogoutDto){

        this.borrowerService.borrowerLogout(borrowerLogoutDto);

        return "Borrower is logged out";
    }
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleException(RegistrationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid logout");
    }
}
