package com.formacion.ejercicio1.rabbitmq;

import com.formacion.ejercicio1.DTO.PaymentDTO;
import com.formacion.ejercicio1.model.Payment;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;


@Component
@Profile("default")
public class RabbitReceiver {

    @Autowired
    private AmqpTemplate amqpTemplate;
    private static Logger logger = LogManager.getLogger(RabbitReceiver.class.toString());

    @RabbitListener(queues = "${rabbitmq.queue}", id = "listener")//The class will be invoked when receiving a msg from a specific queue
    @RabbitHandler//If u send a PaymentDTO object, it will arrive to this method.
    public void receiver(PaymentDTO payment) {
        logger.info("PaymentDTO listener invoked - Consuming Message with Payment Identifier : " + payment.getId());
        amqpTemplate.receive();

    }
}
