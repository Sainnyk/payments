package com.formacion.ejercicio1.service;

import com.formacion.ejercicio1.DTO.PaymentDTO;
import com.formacion.ejercicio1.model.Payment;
import com.formacion.ejercicio1.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

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
        //repository.save(payment);
        feignClient.validateData(payment.getCreditor());
        log.info("Payload sent through Service: {}", payment);
    }

    public Iterable<PaymentDTO> getPayments(){
        return repository.findAll();
    }


    public List<PaymentDTO> getPaymentsByName(String debtor) {
        return repository.findByDebtor(debtor);
    }
}
