package com.formacion.ejercicio1.controllers;

import com.formacion.ejercicio1.model.Payment;
import com.formacion.ejercicio1.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {


    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<Payment> createPayment(@RequestBody Payment payment){
        log.info("Payload received: {}", payment);
        System.out.println(payment.getDebtor());
        System.out.println(payment.getCreditor());
        paymentService.callWiremock(payment);
        return ResponseEntity.ok(payment);

    }
}
