package com.formacion.ejercicio1.controllers;

import com.formacion.ejercicio1.DTO.PaymentDTO;
import com.formacion.ejercicio1.model.Payment;
import com.formacion.ejercicio1.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getPayments(){
        return ResponseEntity.ok((List<PaymentDTO>) paymentService.getPayments());

    }

    @GetMapping("/payments/{debtor}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByName(@PathVariable String debtor){
        return ResponseEntity.ok((List<PaymentDTO>)paymentService.getPaymentsByName(debtor));

    }
}
