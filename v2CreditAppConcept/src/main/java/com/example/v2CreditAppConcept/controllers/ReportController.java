package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.services.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReportController {

    private ContractService contractService;

    @Autowired
    public ReportController(ContractService contractService) {
        this.contractService = contractService;
    }

    @GetMapping ("/contract/report/{id}")
    private ResponseEntity<String> report(@PathVariable long id){


        return new ResponseEntity<>(this.contractService.report(id), HttpStatus.OK);
    }
}
