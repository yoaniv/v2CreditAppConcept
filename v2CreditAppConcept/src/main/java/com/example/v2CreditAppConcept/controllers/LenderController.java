package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.entities.dto.BorrowerLogoutDto;
import com.example.v2CreditAppConcept.entities.dto.LenderLoginDto;
import com.example.v2CreditAppConcept.entities.dto.LenderLogoutDto;
import com.example.v2CreditAppConcept.entities.dto.LenderRegistrationDto;
import com.example.v2CreditAppConcept.exceptions.LoginException;
import com.example.v2CreditAppConcept.exceptions.RegistrationException;
import com.example.v2CreditAppConcept.services.LenderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LenderController {

    private LenderService lenderService;

    public LenderController(LenderService lenderService) {
        this.lenderService = lenderService;
    }

    @PostMapping("/registration/lender")
    public ResponseEntity<String> lenderRegistration(@RequestBody @Valid LenderRegistrationDto lenderRegistrationDto) throws RegistrationException {


        this.lenderService.lenderRegistration(lenderRegistrationDto);

        return new ResponseEntity<>("Lender is registered successfully", HttpStatus.CREATED);
    }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleException(RegistrationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid credentials");
    }

    @PostMapping("/lender/login")
    public ResponseEntity<String> login(@RequestBody @Valid LenderLoginDto lenderLoginDto) throws LoginException {
        this.lenderService.login(lenderLoginDto);
        return new ResponseEntity<>("User " + lenderLoginDto.getUsername() + " is logged in successfully", HttpStatus.OK);
    }
    @ExceptionHandler(LoginException.class)
    public ResponseEntity<String> handleException(LoginException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid credentials");
    }

    @PostMapping("/lender/logout")
    public ResponseEntity<String> logout(@RequestBody LenderLogoutDto lenderLogoutDto){

        this.lenderService.lenderLogout(lenderLogoutDto);

        return new ResponseEntity<>("Lender is logged out",HttpStatus.OK);
    }

}





