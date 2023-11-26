package com.example.v2CreditAppConcept.services;

import com.example.v2CreditAppConcept.entities.Borrower;
import com.example.v2CreditAppConcept.entities.Contract;
import com.example.v2CreditAppConcept.entities.Lender;
import com.example.v2CreditAppConcept.entities.dto.PaymentDto;
import com.example.v2CreditAppConcept.repositories.BorrowerRepository;
import com.example.v2CreditAppConcept.repositories.ContractRepository;
import com.example.v2CreditAppConcept.repositories.LenderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContractServiceTest {

    private ContractService contractServiceTest;

    @Mock
    private ContractRepository mockContractRepository;

    @Mock
    private BorrowerRepository mockBorrowerRepository;

    @Mock
    private LenderRepository mockLenderRepository;



    @BeforeEach
    void setUp() {
        contractServiceTest = new ContractService(mockBorrowerRepository,mockLenderRepository,mockContractRepository);

    }

    @Test
    void contractCreationTest() {
        Borrower borrower = new Borrower();
        borrower.setUsername("borrower");
        Contract contract = new Contract();
        contract.setBorrower(borrower);
        contract.setAmount(500);

        when(mockContractRepository.findContractByBorrower_Username("borrower"))
                .thenReturn(Optional.of(contract));

        Optional<Contract> contractOpt = mockContractRepository.findContractByBorrower_Username("borrower");

        Assertions.assertTrue(contractOpt.isPresent());

        if (contractOpt.isPresent()) {

            Assertions.assertTrue(contractOpt.get().getAmount() == 500);
            Assertions.assertTrue(contractOpt.get().getBorrower().getUsername().equals("borrower"));

        }
    }

    @Test
    void createPaymentTest() {
        // Arrange
        Lender lender = new Lender();
        lender.setId(1);
        lender.setName("lender");
        lender.setAvailableFunds(1000);
        lender.setPassword("password");

        Borrower borrower = new Borrower();
        borrower.setId(1);
        borrower.setUsername("borrower");
        borrower.setLogged(true);
        borrower.setName("borrower");
        borrower.setPassword("tainaparola");

        Contract contract = new Contract();
        contract.setId(1);
        contract.setBorrower(borrower);
        contract.setLender(lender);
        contract.setAmount(1000);

        lender.setAvailableFunds(lender.getAvailableFunds()-contract.getAmount());

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setAmount(300);
        paymentDto.setBorrowerUsername("borrower");

        Optional<Contract> optionalContract = Optional.of(contract);

        when(mockContractRepository.findContractByBorrower_Username("borrower")).thenReturn(optionalContract);
        when(mockContractRepository.save(Mockito.any())).thenReturn(contract);
        when(mockLenderRepository.save(Mockito.any())).thenReturn(lender);
        when(mockBorrowerRepository.save(Mockito.any())).thenReturn(borrower);



        // Act
        contractServiceTest.createPayment(paymentDto);

        // Assert
        Assertions.assertEquals(700, contract.getAmount());
        Assertions.assertTrue(contract.getLender().getAvailableFunds()==300);
    }

    void testBorrowerNotFound(){


    }
}
