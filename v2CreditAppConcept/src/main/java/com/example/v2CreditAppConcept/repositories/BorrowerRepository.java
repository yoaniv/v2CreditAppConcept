package com.example.v2CreditAppConcept.repositories;

import com.example.v2CreditAppConcept.entities.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BorrowerRepository extends JpaRepository<Borrower,Long> {



    Optional<Borrower> findByUsername(String username);
}
