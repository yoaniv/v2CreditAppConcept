package com.example.v2CreditAppConcept.services;

import com.example.v2CreditAppConcept.entities.Lender;
import com.example.v2CreditAppConcept.entities.dto.*;
import com.example.v2CreditAppConcept.repositories.LenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public List<LenderExposedDataInBorrowerSearchDto> getAll(){
        List<LenderExposedDataInBorrowerSearchDto> lenderDtoList =new ArrayList<>();
         List<Lender>lenders=this.lenderRepository.findAll();

        for (Lender lender : lenders) {
            String name=lender.getName();
            int amount=lender.getAvailableFunds();
            LenderExposedDataInBorrowerSearchDto lenderExposedDataInBorrowerSearchDto =new LenderExposedDataInBorrowerSearchDto();
            lenderExposedDataInBorrowerSearchDto.setName(name);
            lenderExposedDataInBorrowerSearchDto.setAmount(amount);
            lenderDtoList.add(lenderExposedDataInBorrowerSearchDto);
        }

         return lenderDtoList;
    }

    public void login(LenderLoginDto lenderLoginDto) {

        String username = lenderLoginDto.getUsername();

        Optional<Lender> optLender = this.lenderRepository.findByUsername(username);


        if (optLender.isPresent()) {
            optLender.get().setLogged(true);
        }

        Lender lender = optLender.get();

        lenderRepository.save(lender);

    }

    public void lenderLogout(LenderLogoutDto lenderLogoutDto){

        Optional<Lender> optionalLender=this.lenderRepository.findByUsername(lenderLogoutDto.getUsername());

        if (optionalLender.isPresent()){
            Lender lender=optionalLender.get();

            lender.setLogged(false);

            this.lenderRepository.save(lender);

        }

    }
}
