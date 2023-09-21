package com.formacion.ejercicio1.rabbitmq;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import org.springframework.context.annotation.Profile;

import java.nio.charset.StandardCharsets;


//Publishes msgs to Rabbit
public class Send {

    private final static String queue = "hello";

    public static void main(String[] argv) throws Exception {

        //Connection to the server:
        ConnectionFactory factory = new ConnectionFactory(); //Crea una conexion a Rabbit
        factory.setHost("localhost");//Nodo de RabbitMQ local. Si fuera en una maquina diferente ponemos el nombre del host o direccion ip
        factory.setUsername("rabbit");
        factory.setPassword("rabbit");
        try (Connection connection = factory.newConnection();//Connection to a Rabbit server instance
             //Create a communication channel between the application and the RabbitMQ server. Channels are where the most part of the API resides to make things (publishing, subscribing..., independently and concurrently)
             Channel channel = connection.createChannel()) { //Channels let you multiplex multiple logical connections over a single physical connection.

            //Send messages:
            channel.queueDeclare(queue, false, false, false, null);
            String message = "Hello World!";
            //Publishes a msg to the Rabbit queue to send a msg. Convert msg to bytes
            channel.basicPublish("", queue, null, message.getBytes(StandardCharsets.UTF_8));
            System.out.println(" [x] Sent '" + message + "'");
        }
    }
}
