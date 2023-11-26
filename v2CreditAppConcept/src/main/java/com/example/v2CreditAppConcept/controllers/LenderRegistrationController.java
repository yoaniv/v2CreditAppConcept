package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.entities.dto.LenderRegistrationDto;
import com.example.v2CreditAppConcept.exceptions.RegistrationException;
import com.example.v2CreditAppConcept.services.LenderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController

public class LenderRegistrationController {

    private LenderService lenderService;

    public LenderRegistrationController(LenderService lenderService) {
        this.lenderService = lenderService;
    }

    @PostMapping("/registration/lender")
    private String lenderRegistration(@RequestBody @Valid LenderRegistrationDto lenderRegistrationDto) throws RegistrationException {


            this.lenderService.lenderRegistration(lenderRegistrationDto);

            return "Lender is registered successfully";
        }

    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleException(RegistrationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid credentials");
    }

    }

