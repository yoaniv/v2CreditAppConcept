package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.entities.dto.BorrowerLoginDto;
import com.example.v2CreditAppConcept.exceptions.BorrowerLoginException;
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
public class BorrowerLoginController {

    private BorrowerService borrowerService;

    @Autowired
    public BorrowerLoginController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @PostMapping("/borrower/login")
    private String login(@RequestBody @Valid BorrowerLoginDto borrowerLoginDto) throws BorrowerLoginException {

        this.borrowerService.login(borrowerLoginDto);

        return borrowerLoginDto.getUsername()+" is logged successfully";
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid credentials");
    }
}
//JSON -> {
//    "username": "borrower",
//    "password": "tainaparola"
//}
