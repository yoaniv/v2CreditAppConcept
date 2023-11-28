package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.entities.dto.PaymentDto;
import com.example.v2CreditAppConcept.exceptions.PaymentException;
import com.example.v2CreditAppConcept.exceptions.RegistrationException;
import com.example.v2CreditAppConcept.services.ContractService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private ContractService contractService;

    @Autowired
    public PaymentController(ContractService contractService) {
        this.contractService = contractService;
    }

    @PutMapping("/borrower-payment")
    public ResponseEntity<String> createPayment(@RequestBody @Valid PaymentDto paymentDto)throws PaymentException{

this.contractService.createPayment(paymentDto);

        return new ResponseEntity<>( "Payment is successful",HttpStatus.OK);
    }
    @ExceptionHandler(RegistrationException.class)
    public ResponseEntity<String> handleException(PaymentException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid payment");
    }
}
