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
         feignClient.sendData(payment);
         return ResponseEntity.ok("Enviado correctamente");
    }


}
