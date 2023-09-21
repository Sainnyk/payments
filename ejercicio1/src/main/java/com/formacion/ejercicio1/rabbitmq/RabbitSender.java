package com.formacion.ejercicio1.rabbitmq;

import com.formacion.ejercicio1.DTO.PaymentDTO;
import com.formacion.ejercicio1.rabbitmq.RabbitConfig;
import org.springframework.amqp.core.AmqpTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.Queue;


@Component
public class RabbitSender {
    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private Queue queue;
    private static Logger logger = LogManager.getLogger(RabbitSender.class.toString());



    public void send(PaymentDTO payment) {
        amqpTemplate.convertAndSend(queue.getName(), payment); //Adds a msg to the queue after conversion
        logger.info("Sending Message to the Queue : " + queue.getName()+ " "+ payment.toString());
    }
}
