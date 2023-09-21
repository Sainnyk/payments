package com.formacion.ejercicio1.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;

//Listens for msgs from Rabbit
public class Recv {

    //Declare the queue because you may start the consumer before the publisher
    private final static String queue = "hello";

    public static void main(String[] argv) throws Exception {

        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setUsername("rabbit");
        factory.setPassword("rabbit");

        //We don't use try-with-resource because we don't want the connection and channel to close. We want the consumer listening
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(queue, false, false, false, null);
        System.out.println(" [*] Waiting for messages");

        //Deliver us the messages from the queue: will push us msgs asynchronously, so we provide a callback in the form of an object that will buffer the msgs until we are rdy to use them (DeliverCallBack)
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println(" [x] Received '" + message + "'");
        };
        channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });
    }
}

