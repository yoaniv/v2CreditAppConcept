package com.example.v2CreditAppConcept.services;

import com.example.v2CreditAppConcept.entities.Borrower;
import com.example.v2CreditAppConcept.entities.dto.BorrowerLoginDto;
import com.example.v2CreditAppConcept.entities.dto.BorrowerLogoutDto;
import com.example.v2CreditAppConcept.entities.dto.BorrowerRegistrationDto;
import com.example.v2CreditAppConcept.exceptions.BorrowerLoginException;
import com.example.v2CreditAppConcept.repositories.BorrowerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class BorrowerService {

    private BorrowerRepository borrowerRepository;

    @Autowired
    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }

    public void registration(BorrowerRegistrationDto borrowerRegistrationDto){


        Borrower borrower=new Borrower();

        borrower.setUsername(borrowerRegistrationDto.getUsername());
        borrower.setPassword(borrowerRegistrationDto.getPassword());
        borrower.setName(borrowerRegistrationDto.getName());
        borrower.setEmail(borrowerRegistrationDto.getEmail());


        this.borrowerRepository.save(borrower);

    }

    public void login(BorrowerLoginDto borrowerLoginDto) {

            String username = borrowerLoginDto.getUsername();

            Optional<Borrower> optBorrower = this.borrowerRepository.findByUsername(username);


            if (optBorrower.isPresent()) {
                optBorrower.get().setLogged(true);
            }

            Borrower borrower = optBorrower.get();

            borrowerRepository.save(borrower);

        }



    public void borrowerLogout(BorrowerLogoutDto borrowerLogoutDto){

        Optional<Borrower> optionalBorrower=this.borrowerRepository.findByUsername(borrowerLogoutDto.getUsername());

        if (optionalBorrower.isPresent()){
            Borrower borrower=optionalBorrower.get();

            borrower.setLogged(false);

            this.borrowerRepository.save(borrower);

        }

    }

}
