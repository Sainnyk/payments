package com.formacion.wiremock.service;

import org.iban4j.Iban;
import org.springframework.stereotype.Service;

@Service
public class ReceiverService {
    public ReceiverService() {
    }

    public void validateIban (Iban creditor) throws Exception {

        if(!creditor.getBankCode().equals("0049")) {
            throw new Exception("El IBAN no pertenece al Banco Santander");
        }
    }
}
