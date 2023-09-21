package com.formacion.ejercicio1.rabbitmq;


import com.formacion.ejercicio1.Ejercicio1Application;
import com.rabbitmq.client.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.IOException;


import java.util.concurrent.TimeoutException;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = Ejercicio1Application.class)
@Testcontainers
@ActiveProfiles({"default","rabbitmq"})
class SendTest {


    private static final RabbitMQContainer rabbitContainer = new RabbitMQContainer("rabbitmq:management").withUser("rabbit","rabbit");
    private static final String queue = "test_queue";
    private ConnectionFactory factory;
    private Connection connection;
    private Channel channel;

    @BeforeAll
    static void beforeAll(){

        rabbitContainer.start();

    }
    @BeforeEach
    public void setUp() throws Exception {
        // Arrange: Set up the RabbitMQ connection and channel
        factory = new ConnectionFactory();
        factory.setHost(rabbitContainer.getHost());
        factory.setUsername("rabbit");
        factory.setPassword("rabbit");

        connection = factory.newConnection();
        channel = connection.createChannel();
        channel.queueDeclare(queue, false, false, false, null);
    }

    @AfterAll
    static void afterAll() {
        rabbitContainer.stop();
    }
    @AfterEach
    public void tearDown() throws Exception {
        // Arrange: Clean up resources
        channel.close();
        connection.close();
    }


    @DisplayName("given a msg publish it to a queue and consume it")
    @Test
    void sendMsg() throws IOException{
        // Act: Send a message to the queue
        String message = "Hello world";
        channel.basicPublish("", queue, null, message.getBytes());
        assertThat(queue).isNotNull();

        // Assert: Check if the message is received
        GetResponse response = channel.basicGet(queue, true);

        //Check if the response is not empty
        assertThat(response).isNotNull();

        //Check if the message is consumed-> queue should be empty
        assertThat(!queue.contains("Hello world"));

        //Check if the message received is the same as the one sent.
        String receivedMessage = new String(response.getBody());
        assertThat(message).isEqualTo(receivedMessage);
        /*
        String queue = "hello";


        assertThat(channel)
                .isNotNull();

        //Send messages:

        String message = "Hello World!";
        channel.basicPublish("", queue, null, message.getBytes(StandardCharsets.UTF_8));


        System.out.println(" [x] Sent '" + message + "'");

        //RCV MSGS
        channel.queueDeclare(queue, false, false, false, null);
        System.out.println(" [*] Waiting for messages");

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String messageReceived = new String(delivery.getBody(),StandardCharsets.UTF_8);
            System.out.println(" [x] Received '" + messageReceived + "'");
        };

        channel.basicConsume(queue, true, deliverCallback, consumerTag -> { });

        assertThat(deliverCallback)
                .isNotNull();*/
    }


}