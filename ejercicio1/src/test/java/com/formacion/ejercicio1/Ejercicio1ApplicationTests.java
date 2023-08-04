package com.formacion.ejercicio1;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;


import static com.github.tomakehurst.wiremock.client.WireMock.*;


	@SpringBootTest
	@AutoConfigureMockMvc
	@AutoConfigureWireMock(port = 8089)
	class Ejercicio1ApplicationTests {


		@Autowired
		private MockMvc mockMvc;



		@DisplayName("given a debtor creditor and amount" +
				"when I post it to my api" +
				"then the ibans are validated " +
				"and the response is ok")
		@Test
		void wiremockTest() throws Exception {
			stubFor(get(urlPathMatching("/ibans/ES5300490418450200051332")).willReturn(ok()));
//        stubFor(get(urlPathMatching("/ibans/creditorBic")).willReturn(ok()));


			//language=JSON
			final var payloadTemplate = """
                {
                  "debtor": "ES9121000418450200051332",
                  "creditor": "ES5300490418450200051332",
                  "amount": 1.0
                }
                """;
			mockMvc.perform(post("/payment")
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(payloadTemplate))
					.andExpect(status().isOk());

			WireMock.verify(getRequestedFor(urlPathMatching("/ibans/ES5300490418450200051332")));
//        WireMock.verify(getRequestedFor(urlPathMatching("/ibans/creditorBic")));
		}

		@DisplayName("given a debtor creditor and amount" +
				"when I post it to my api" +
				"then the ibans are validated " +
				"and the response is bad request")
		@Test
		void testInvalidIbanValidation() throws Exception {
			stubFor(get(urlPathMatching("/ibans/ES9100490418450200051332")).willReturn(badRequest()));
//        stubFor(get(urlPathMatching("/ibans/creditorBic")).willReturn(ok()));


			//language=JSON
			final var payloadTemplate = """
                {
                  "debtor": "ES9121000418450200051332",
                  "creditor": "ES9100490418450200051332",
                  "amount": 1.0
                }
                """;
			mockMvc.perform(post("/payment")
							.contentType(MediaType.APPLICATION_JSON_VALUE)
							.content(payloadTemplate))
					.andExpect(status().isBadRequest());

//        WireMock.verify(getRequestedFor(urlPathMatching("/ibans/creditorBic")));
		}
	}



