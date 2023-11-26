package com.example.v2CreditAppConcept.entities.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class LenderRegistrationDto {

    @NotEmpty
    @Size(min = 2,max = 20)
    private String username;
    @NotEmpty
    @Size(min = 2,max = 20)
    private String password;
    @NotEmpty
    @Size(min = 2,max = 20)
    private String name;
    @NotEmpty
    @Size(min = 2,max = 20)
    private String email;

    private int availableFunds;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAvailableFunds() {
        return availableFunds;
    }

    public void setAvailableFunds(int availableFunds) {
        this.availableFunds = availableFunds;
    }
}
