package com.formacion.ejercicio1.rabbitmq;

import com.formacion.ejercicio1.DTO.PaymentDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value="/rabbit")
public class RabbitController {
    @Autowired
    RabbitSender rabbitMQSender;
    @PostMapping(value = "/sender")
    public String producer(@RequestBody PaymentDTO payment) {
        log.info("Dentro: {}", payment);
        rabbitMQSender.send(payment);
        return "Message sent to the RabbitMQ Queue Successfully";
    }
}

