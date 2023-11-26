package com.example.v2CreditAppConcept.entities.dto;

import com.example.v2CreditAppConcept.entities.Borrower;
import com.example.v2CreditAppConcept.entities.Lender;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PaymentDto {

    @NotEmpty
    @Size(min = 2,max = 20)
    private String borrowerUsername;
    @Positive
    @Min(1)
    private Integer amount;


    public String getBorrowerUsername() {
        return borrowerUsername;
    }

    public void setBorrowerUsername(String borrowerUsername) {
        this.borrowerUsername = borrowerUsername;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
