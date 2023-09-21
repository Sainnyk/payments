package com.formacion.wiremock.service;

import com.formacion.wiremock.DTO.PaymentDTO;

import com.formacion.wiremock.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentService {

    private final PaymentRepository repository;

    public PaymentService(PaymentRepository repository) {
        this.repository = repository;
    }


    public Iterable<PaymentDTO> getPayments(){
        return repository.findAll();
    }


    public List<PaymentDTO> getPaymentsByName(String debtor) {
        return repository.findByDebtor(debtor);
    }
}
