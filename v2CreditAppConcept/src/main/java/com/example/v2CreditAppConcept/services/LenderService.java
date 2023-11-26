package com.example.v2CreditAppConcept.services;

import com.example.v2CreditAppConcept.entities.Lender;
import com.example.v2CreditAppConcept.entities.dto.LenderRegistrationDto;
import com.example.v2CreditAppConcept.repositories.LenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LenderService {

    private LenderRepository lenderRepository;

    @Autowired
    public LenderService(LenderRepository lenderRepository) {
        this.lenderRepository = lenderRepository;
    }

    public void lenderRegistration(LenderRegistrationDto lenderRegistrationDto){

        Lender lender=new Lender();
        lender.setUsername(lenderRegistrationDto.getUsername());
        lender.setPassword(lenderRegistrationDto.getPassword());
        lender.setName(lenderRegistrationDto.getName());
        lender.setEmail(lenderRegistrationDto.getEmail());
        lender.setAvailableFunds(lenderRegistrationDto.getAvailableFunds());

        this.lenderRepository.save(lender);
    }

    public List<Lender> getAll(){

        return this.lenderRepository.findAll();
    }
}
