package com.formacion.ejercicio1.service;

import com.formacion.ejercicio1.model.Payment;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(value = "wiremock",url = "http://localhost:8080/receiver")//nombre y ruta del receptor
public interface ITransmitter {
    @PostMapping ("/getpayment")
    ResponseEntity<Payment> sendData(@RequestBody Payment payment);
}
