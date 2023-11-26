package com.example.v2CreditAppConcept.entities;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private Lender lender;

    @OneToOne
    private Borrower borrower;

    @Column(name = "amount")
    private int amount;

    public Contract() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Lender getLender() {
        return lender;
    }

    public void setLender(Lender lender) {
        this.lender = lender;
    }

    public Borrower getBorrower() {
        return borrower;
    }

    public void setBorrower(Borrower borrower) {
        this.borrower = borrower;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Contract:" +
                "id=" + id +
                ", lender=" + lender +
                ", borrower=" + borrower +
                ", amount=" + amount;
    }
}
