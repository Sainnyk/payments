package com.formacion.ejercicio1.service;

import com.formacion.ejercicio1.model.Payment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PaymentService {
    private final ITransmitter feignClient;
    public PaymentService(ITransmitter feignClient) {
        this.feignClient = feignClient;
    }

    public ResponseEntity<String> callWiremock(Payment payment) {
        System.out.println("SERVICE:");
        System.out.println(payment.getCreditor());
        System.out.println(payment.getDebtor());
        System.out.println(payment.getAmount());
        feignClient.sendData(payment);
        System.out.println("Enviado a wiremock");
         return ResponseEntity.ok("Enviado correctamente");
    }


}
