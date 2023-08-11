package com.formacion.ejercicio1.service;

import com.formacion.ejercicio1.model.Payment;
import com.formacion.ejercicio1.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PaymentService {
    private final ITransmitter feignClient;
    private final PaymentRepository repository;


    public PaymentService(ITransmitter feignClient, PaymentRepository repository) {
        this.feignClient = feignClient;
        this.repository = repository;
    }

    public void callWiremock(Payment payment) {
        log.info("Payload received in Service: {}", payment);
        repository.save(payment);
        feignClient.validateData(payment.getCreditor());
        log.info("Payload sent through Service: {}", payment);
    }


}
