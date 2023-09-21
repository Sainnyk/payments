package com.formacion.ejercicio1.rabbitmq;

import com.formacion.ejercicio1.Ejercicio1Application;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import org.junit.jupiter.api.*;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.RabbitMQContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = Ejercicio1Application.class)
@Testcontainers
@ActiveProfiles({"default","rabbitmq"})
class RabbitSenderTest {

    @Autowired
    private RabbitConfig rabbitConfig;

    @Autowired
    private RabbitSender sender;
    @Autowired
    private RabbitReceiver receiver;

    private Channel channel;
    private Connection connection;

    private static final RabbitMQContainer rabbitContainer = new RabbitMQContainer("rabbitmq:management").withUser("rabbit","rabbit");
    private static final String queue = "test_queue";


    @BeforeAll
    static void beforeAll(){
            rabbitContainer.start();
        }

    @BeforeEach
    public void setUp() throws Exception {
        // Arrange: Set up the RabbitMQ connection and channel
        ConnectionFactory factory = new ConnectionFactory();

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

/*
    @DisplayName("Basic: given a msg publish it to a queue and consume it")
    @Test
    void sendMsg() throws IOException {
        // Act: Send a message to the queue
        String message1 = "Hello world";
        channel.basicPublish("", queue, null, message1.getBytes());
        assertThat(queue).isNotNull();

        // Assert: Check if the message is received
        GetResponse response = channel.basicGet(queue, true);

        //Check if the response is not empty
        assertThat(response).isNotNull();

        //Check if the message is consumed-> queue should be empty
        assertThat(!queue.contains("Hello world"));

        //Check if the message received is the same as the one sent.
        String receivedMessage = new String(response.getBody());
        assertThat(message1).isEqualTo(receivedMessage);
    }*/
    @DisplayName("AmqTemplate: given a msg publish it to a queue and consume it")
    @Test
    void checkRabbitMqCommunication() {
        AmqpTemplate amqpTemplate = rabbitConfig.amqpTemplate(rabbitConfig.connectionFactory());
        String message = "Hello";
        System.out.println(message);

        //Send msg
        amqpTemplate.convertAndSend(queue, message);
        //Assert that there's a mssg
        assertThat(queue).isNotNull();

        // Assert: Check if the message is received
        Message response = amqpTemplate.receive(queue);
        //Check if the response is not empty
        assertThat(response).isNotNull();

        //Check if the message received is the same as the one sent.
        String receivedMessage = new String(response.getBody());
        receivedMessage= receivedMessage.replace("\"", "");
        System.out.println(receivedMessage);
        assertThat(receivedMessage).isEqualTo(message);
    }

}