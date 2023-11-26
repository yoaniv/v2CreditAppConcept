package com.example.v2CreditAppConcept.services;

import com.example.v2CreditAppConcept.entities.Borrower;
import com.example.v2CreditAppConcept.entities.Contract;
import com.example.v2CreditAppConcept.entities.Lender;
import com.example.v2CreditAppConcept.entities.dto.ContractCreationDto;
import com.example.v2CreditAppConcept.entities.dto.PaymentDto;
import com.example.v2CreditAppConcept.repositories.BorrowerRepository;
import com.example.v2CreditAppConcept.repositories.ContractRepository;
import com.example.v2CreditAppConcept.repositories.LenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ContractService {

    private BorrowerRepository borrowerRepository;
    private LenderRepository lenderRepository;
    private ContractRepository contractRepository;

    @Autowired
    public ContractService(BorrowerRepository borrowerRepository, LenderRepository lenderRepository, ContractRepository contractRepository) {
        this.borrowerRepository = borrowerRepository;
        this.lenderRepository = lenderRepository;
        this.contractRepository = contractRepository;
    }



    public void contractCreation(ContractCreationDto contractCreationDto){

        Contract contract=new Contract();
        Optional<Borrower> optBorrower=this.borrowerRepository.findByUsername(contractCreationDto.getBorrowerUsername());
        optBorrower.ifPresent(contract::setBorrower);

        if (optBorrower.get().isLogged()==true) {

            Borrower borrower = optBorrower.get();
            borrower.setAmountNeeded(optBorrower.get().getAmountNeeded() + contractCreationDto.getAmount());
            this.borrowerRepository.save(borrower);

            Optional<Lender> optLender = this.lenderRepository.findByUsername(contractCreationDto.getLenderUsername());
            optLender.ifPresent(contract::setLender);

            Lender lender = optLender.get();
            lender.setAvailableFunds(optLender.get().getAvailableFunds() - contractCreationDto.getAmount());
            this.lenderRepository.save(lender);

            contract.setAmount(contractCreationDto.getAmount());
            this.contractRepository.save(contract);
        }
    }

    public void createPayment(PaymentDto paymentDto){
        Optional<Contract>optionalContract=this.contractRepository.findContractByBorrower_Username(paymentDto.getBorrowerUsername());

        int instalment=paymentDto.getAmount();

        if (optionalContract.isPresent()){

            Contract contract=optionalContract.get();
            contract.setAmount(optionalContract.get().getAmount()-instalment);

            Lender lender=optionalContract.get().getLender();
            lender.setAvailableFunds(optionalContract.get().getLender()
                    .getAvailableFunds()+instalment);
            this.lenderRepository.save(lender);

            Borrower borrower = contract.getBorrower();
            borrower.setAmountNeeded(borrower.getAmountNeeded() - instalment);
            this.borrowerRepository.save(borrower);

            this.contractRepository.save(contract);
        }
    }

    public String report(long id){
        Optional<Contract>optContract=this.contractRepository.findById(id);

        if (optContract.isPresent()){
            Contract contract=optContract.get();

            long contractId=contract.getId();
            int amountLeft=contract.getAmount();

            String currentTimeReport="Contract with id:"+contractId+" has amount left:"+amountLeft;

        return currentTimeReport;
        }
        String notFound="Contract is not found";

        return notFound;
    }

    public int deleteContract(long id){

        Optional<Contract> optionalContract=this.contractRepository.findById(id);

        if (optionalContract.isPresent()){
            Contract contract=optionalContract.get();

            if (contract.getAmount()==0){
                this.contractRepository.delete(contract);

                return 1;

            }
        }

            return 0;
    }
}
