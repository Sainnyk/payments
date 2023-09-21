package com.formacion.ejercicio1.rabbitmq;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.amqp.RabbitAutoConfiguration;



@SpringBootApplication(exclude={RabbitAutoConfiguration.class})
public class RabbitApplication {
    public static void main(String[] args) {
        SpringApplication.run(RabbitApplication.class, args);
        }
    }

