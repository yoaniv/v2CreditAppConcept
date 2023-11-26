package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.services.ContractService;
import org.hibernate.annotations.SQLDeleteAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContractDeletionController {

    private ContractService contractService;

    @Autowired
    public ContractDeletionController(ContractService contractService) {
        this.contractService = contractService;
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

