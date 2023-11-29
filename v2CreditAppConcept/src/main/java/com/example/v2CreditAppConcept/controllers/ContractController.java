package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.entities.dto.ContractCreationDto;
import com.example.v2CreditAppConcept.entities.dto.LenderExposedDataInBorrowerSearchDto;
import com.example.v2CreditAppConcept.exceptions.ContractCreationException;
import com.example.v2CreditAppConcept.services.ContractService;
import com.example.v2CreditAppConcept.services.LenderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ContractController {
    private LenderService lenderService;
    private ContractService contractService;

    public ContractController(LenderService lenderService, ContractService contractService) {
        this.lenderService = lenderService;
        this.contractService = contractService;
    }

    public void setLenderService(LenderService lenderService) {
        this.lenderService = lenderService;
    }

    @GetMapping("/all-lenders")
    private List<LenderExposedDataInBorrowerSearchDto> getAllLenders(){
        return this.lenderService.getAll();
    }


    @PostMapping("/contract/create")
    public ResponseEntity<String> createContract(@RequestBody @Valid ContractCreationDto contractCreationDto)throws ContractCreationException {
        this.contractService.contractCreation(contractCreationDto);
        return new ResponseEntity<>("Contract is created", HttpStatus.CREATED);
    }

    @ExceptionHandler(ContractCreationException.class)
    public ResponseEntity<String> handleException(ContractCreationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Invalid credentials");
    }


    @DeleteMapping("/contract/delete/{id}")
    private String delete(@PathVariable long id){
        int num= this.contractService.deleteContract(id);

        if (num==1){
            return "Contract is deleted successfully";
        }
        else {
            return "Contract cannot be deleted or not exist";
        }
    }
}
