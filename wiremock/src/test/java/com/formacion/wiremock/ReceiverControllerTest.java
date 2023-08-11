package com.formacion.wiremock;

import com.formacion.wiremock.controller.ReceiverController;
import com.formacion.wiremock.model.Payment;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.iban4j.Iban;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.bind.annotation.RequestBody;


import java.math.BigDecimal;


import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Assertions.assertThat;


class ReceiverControllerTest {

    private WireMockServerConfig wireMockServerConfig;
    private ReceiverController receiverController;


    @BeforeEach
    public void setUp() {
        wireMockServerConfig = new WireMockServerConfig();
        wireMockServerConfig.startWireMock();
    }

    @AfterEach
    public void tearDown() {
        wireMockServerConfig.stopWireMockServer();
    }

    @Test
    void getValidData() throws Exception {
        Payment payment2 = new Payment();
        payment2.setDebtor(Iban.valueOf("ES9121000418450200051332"));
        payment2.setCreditor(Iban.valueOf("ES5300490418450200051332"));
        payment2.setAmount(BigDecimal.valueOf(100.0));

        //Assuming response is the actual response from the other ms
        ResponseEntity<Payment> response = receiverController.getData(payment2);

        //Configures WireMoc to handle the expected request from the first microservice
        stubFor(post(urlEqualTo("/receiver/getpayment"))
                .withRequestBody(matchingJsonPath("$.debtor"))
                .withRequestBody(matchingJsonPath("$.creditor"))
                .withRequestBody(matchingJsonPath("$.amount"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")));
                        //.withBody("{ \"status\": \"SUCCESS\" }")));

        //Perform the request to this microservice
        //String response = mockMvc.perform(MockMvcRequestBuilders.post())


        // Assert the behavior of the first microservice based on the response from the second microservice
      //   assertThat(response.equals(payment2));

    }

    @Test
    public void getInvalidData() {
        Payment payment = new Payment();
        payment.setDebtor(Iban.valueOf("ES9121000418450200051332"));
        payment.setCreditor(Iban.valueOf("ES9121000418450200051332"));
        payment.setAmount(BigDecimal.valueOf(100.0));

        stubFor(post(urlEqualTo("/receiver/getpayment"))
                .willReturn(aResponse()
                        .withStatus(400)
                        .withHeader("Content-Type", "application/json")
                        .withBody("{ \"isValid\": false }")));


    }
}