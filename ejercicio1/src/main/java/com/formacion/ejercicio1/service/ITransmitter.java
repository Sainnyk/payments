package com.formacion.ejercicio1.service;

import com.formacion.ejercicio1.model.Payment;
import org.iban4j.Iban;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;


@FeignClient(value = "client",url = "http://localhost:8089")//nombre del cliente y ruta del receptor
public interface ITransmitter {
    @GetMapping("/ibans/{creditor}")
    Iban validateData(@PathVariable Iban creditor);
}
