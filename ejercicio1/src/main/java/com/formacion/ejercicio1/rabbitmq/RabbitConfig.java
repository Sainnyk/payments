package com.formacion.ejercicio1.rabbitmq;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.context.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.ConditionalRejectingErrorHandler;
import org.springframework.amqp.rabbit.support.ListenerExecutionFailedException;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.util.ErrorHandler;


@Configuration
@EnableRabbit
@Profile("default")
public class RabbitConfig {

    @Value("${rabbitmq.queue}")
    private String queueName;
    @Value("${rabbitmq.exchange}")
    private String exchange;
    @Value("${rabbitmq.routingkey}")
    private String routingkey;
    @Value("${rabbitmq.username}")
    private String username;
    @Value("${rabbitmq.password}")
    private String password;
    @Value("${rabbitmq.host}")
    private String host;
    @Value("${rabbitmq.virtualhost}")
    private String virtualHost;
    @Value("${rabbitmq.reply.timeout}")
    private Integer replyTimeout;
    @Value("${rabbitmq.concurrent.consumers}")
    private Integer concurrentConsumers;
    @Value("${rabbitmq.max.concurrent.consumers}")
    private Integer maxConcurrentConsumers;

    ///////////////////////////////////////////////////////////////////////////////////////////

    @Bean
    public Queue queue() {
        Queue queue = new Queue(queueName, false);//Non durable = queue and msgs will be removed once Rabbit is stopped.
        System.out.println("Created queue with name: " + queueName);
        return queue;
    }
    @Bean
    public DirectExchange exchange() { //It routes messages to a queue by matching a complete routing key.
        return new DirectExchange(exchange);
    }
    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) { //to tie an exchange with the queue.
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }
    @Bean
    public MessageConverter jsonMessageConverter() { //Ensure msgs are delivered in JSON format
        ObjectMapper objectMapper = new ObjectMapper();
        return new Jackson2JsonMessageConverter(objectMapper);
    }
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setVirtualHost(virtualHost);
        connectionFactory.setHost(host);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        System.out.println("Created connection with admnin: " + username);
        return connectionFactory;
    }
    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory) { //sending messages to the queue
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setDefaultReceiveQueue(queueName);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        rabbitTemplate.setReplyAddress(queue().getName());
        rabbitTemplate.setReplyTimeout(replyTimeout);
        rabbitTemplate.setUseDirectReplyToContainer(false);
        return rabbitTemplate;
    }
    @Bean
    public AmqpAdmin amqpAdmin() { //It’s useful if we need queues to be automatically declared and bounded.
        return new RabbitAdmin(connectionFactory());
    }
    @Bean
    //required since the infrastructure looks for a bean rabbitListenerContainerFactory as the factory’s source for creating message listener containers by default.
    //Configure the behavior of message listeners
    //It's used with @RabbitListener (in the receiver class) to specify methods that should be invoked when a msg is received from a queue.
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        final SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory());
        factory.setMessageConverter(jsonMessageConverter());
        factory.setConcurrentConsumers(concurrentConsumers);
        factory.setMaxConcurrentConsumers(maxConcurrentConsumers);
        factory.setErrorHandler(errorHandler());
        return factory;
    }
    @Bean
    public ErrorHandler errorHandler() { //return some user-friendly Error
        return new ConditionalRejectingErrorHandler(new MyFatalExceptionStrategy());
    }
    public static class MyFatalExceptionStrategy extends ConditionalRejectingErrorHandler.DefaultExceptionStrategy {
        private final Logger logger = LogManager.getLogger(getClass());
        @Override
        public boolean isFatal(Throwable t) {
            if (t instanceof ListenerExecutionFailedException) {
                ListenerExecutionFailedException lefe = (ListenerExecutionFailedException) t;
                logger.error("Failed to process inbound message from queue "
                        + lefe.getFailedMessage().getMessageProperties().getConsumerQueue()
                        + "; failed message: " + lefe.getFailedMessage(), t);
            }
            return super.isFatal(t);
        }
    }
}
