package com.example.v2CreditAppConcept.controllers;

import com.example.v2CreditAppConcept.services.ContractService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ReportController.class)
public class ReportControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ContractService contractService;

    @Test
    void testReport() throws Exception {
        long contractId = 1L;



        when(contractService.report(contractId)).thenReturn("test is OK");


        mockMvc.perform(get("/contract/report/{id}", contractId))
                .andExpect(status().isOk());

    }
}