package com.example.v2CreditAppConcept.repositories;

import com.example.v2CreditAppConcept.entities.Borrower;
import com.example.v2CreditAppConcept.entities.Lender;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LenderRepository extends JpaRepository<Lender,Long> {

    List<Lender> findAll();

    Optional<Lender> findByUsername(String username);
}
