package com.example.v2CreditAppConcept.repositories;

import com.example.v2CreditAppConcept.entities.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {

    Optional<Contract> findContractByBorrower_Username(String borrowerUsername);
}
