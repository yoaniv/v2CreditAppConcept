package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.entities.dto.BorrowerLoginDto;
import com.example.v2CreditAppConcept.entities.dto.BorrowerLogoutDto;
import com.example.v2CreditAppConcept.entities.dto.BorrowerRegistrationDto;
import com.example.v2CreditAppConcept.exceptions.LoginException;
import com.example.v2CreditAppConcept.exceptions.RegistrationException;
import com.example.v2CreditAppConcept.services.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BorrowerController {
    private BorrowerService borrowerService;

    @Autowired
    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping("/borrower/login")
    public ResponseEntity<String> login(@RequestBody @Valid BorrowerLoginDto borrowerLoginDto) throws LoginException {
        this.borrowerService.login(borrowerLoginDto);
        return new ResponseEntity<>("User " + borrowerLoginDto.getUsername() + " is logged in successfully", HttpStatus.OK);
    }
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleException(LoginException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid credentials");
    }

    @PostMapping("/borrower/logout")
    public ResponseEntity<String> logout(@RequestBody BorrowerLogoutDto borrowerLogoutDto){

        this.borrowerService.borrowerLogout(borrowerLogoutDto);

        return new ResponseEntity<>("Borrower is logged out",HttpStatus.OK);
    }


    @PostMapping("/registration/borrower")
    public ResponseEntity<String> registration(@RequestBody @Valid BorrowerRegistrationDto borrowerRegistrationDto)throws RegistrationException {

        this.borrowerService.registration(borrowerRegistrationDto);

        return new ResponseEntity<>("Borrower is registered successfully", HttpStatus.CREATED);
    }
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleException(RegistrationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid credentials");
    }

}
