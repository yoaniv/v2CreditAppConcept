package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.entities.Lender;
import com.example.v2CreditAppConcept.entities.dto.ContractCreationDto;
import com.example.v2CreditAppConcept.exceptions.ContractCreationException;
import com.example.v2CreditAppConcept.services.ContractService;
import com.example.v2CreditAppConcept.services.LenderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContractCreationController {

    private LenderService lenderService;
    private ContractService contractService;

    public ContractCreationController(LenderService lenderService, ContractService contractService) {
        this.lenderService = lenderService;
        this.contractService = contractService;
    }

    public void setLenderService(LenderService lenderService) {
        this.lenderService = lenderService;
    }

    @GetMapping("/all-lenders")
    private List<Lender> getAllLenders(){

        return this.lenderService.getAll();
    }

    @PostMapping("/contract/create")
    private String createContract(@RequestBody @Valid ContractCreationDto contractCreationDto)throws ContractCreationException{

        this.contractService.contractCreation(contractCreationDto);

        return "Contract is created";
    }
    @ExceptionHandler(ContractCreationException.class)
    public ResponseEntity<String> handleException(ContractCreationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid credentials");
    }
}
