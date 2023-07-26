package com.formacion.ejercicio1.controllers;

import com.formacion.ejercicio1.model.Payment;
import com.formacion.ejercicio1.service.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PaymentController {


    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }
    @PostMapping
    public ResponseEntity<String> createPayment(@RequestBody Payment payment){
        paymentService.callWiremock(payment);
        return ResponseEntity.ok("Objeto enviado");

    }
}
