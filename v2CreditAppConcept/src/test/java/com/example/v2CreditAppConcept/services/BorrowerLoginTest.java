package com.example.v2CreditAppConcept.services;

import com.example.v2CreditAppConcept.entities.Borrower;
import com.example.v2CreditAppConcept.entities.dto.BorrowerLoginDto;
import com.example.v2CreditAppConcept.repositories.BorrowerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
 class BorrowerLoginTest {

    private BorrowerService testBorrowerService;

    @Mock
    private BorrowerRepository mockBorrowerRepository;

    @BeforeEach
    void setUp() {
        testBorrowerService = new BorrowerService(mockBorrowerRepository);

    }

    @Test
    void borrowerLoginTest(){

        Borrower borrower=new Borrower();

        borrower.setId(1);
        borrower.setName("borrower");
        borrower.setPassword("parola");
        borrower.setLogged(false);
        borrower.setEmail("borrower@email.com");
        borrower.setUsername("borrower");


        when(mockBorrowerRepository.findByUsername("borrower"))
                .thenReturn(Optional.of(borrower));

        BorrowerLoginDto borrowerLoginDto=new BorrowerLoginDto();
        borrowerLoginDto.setUsername("borrower");
        borrowerLoginDto.setPassword("parola");

        this.testBorrowerService.login(borrowerLoginDto);


        Assertions.assertTrue(borrower.isLogged());

    }
}
