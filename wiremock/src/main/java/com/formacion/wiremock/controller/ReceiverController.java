package com.formacion.wiremock.controller;

import com.formacion.wiremock.model.Payment;
import com.formacion.wiremock.service.ReceiverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/receiver")
public class ReceiverController {

    private final ReceiverService receiverService;

    public ReceiverController(ReceiverService receiverService) {
        this.receiverService = receiverService;
    }

    @PostMapping(value = "/getpayment", produces = "application/json")//produces especifica el tipo de contenido
    @ResponseBody //Asegura que se devuelva el cuerpo de la respuesta en vez de buscar una vista
    public ResponseEntity<Payment> getData(@RequestBody Payment payment) throws Exception {//Debe devolver lo mismo que el método de FeignClient
        System.out.println(payment.getCreditor());
        System.out.println(payment.getDebtor());
        System.out.println(payment.getAmount());
        System.out.println("Info recibidad");
        receiverService.validateIban(payment.getCreditor());
        return ResponseEntity.ok(payment);
    }
}
