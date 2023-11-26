package com.example.v2CreditAppConcept.services;

import com.example.v2CreditAppConcept.entities.dto.BorrowerRegistrationDto;
import com.example.v2CreditAppConcept.repositories.BorrowerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BorrowerRegistrationTest {

    private BorrowerService borrowerServiceTest;

    @Mock
    private BorrowerRepository mockBorrowerRepository;

    @BeforeEach
    void setUp() {
        borrowerServiceTest = new BorrowerService(mockBorrowerRepository);
    }

    @Test
    void borrowerRegistration(){

        BorrowerRegistrationDto borrowerRegistrationDto=new BorrowerRegistrationDto();

        borrowerRegistrationDto.setEmail("borrower@email.com");
        borrowerRegistrationDto.setName("borrower");
        borrowerRegistrationDto.setPassword("password");
        borrowerRegistrationDto.setUsername("borrower");


        borrowerServiceTest.registration(borrowerRegistrationDto);

        var borrower=this.mockBorrowerRepository.findByUsername("borrower");

        if (borrower.isPresent()) {

            Assertions.assertTrue(borrower.isPresent());

        }
    }
}
