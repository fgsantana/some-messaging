package com.github.fgsantana.rabbitmqconsumer.service;

import com.github.fgsantana.rabbitmqconsumer.repository.ConsumerRepository;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

@Service
public class ConsumerService {

    @Value("${rabbitmq.queue.name}")
    String queueName;

    final ConnectionFactory connectionFactory;
    final ConsumerRepository consumerRepository;

    public ConsumerService(ConnectionFactory connectionFactory, ConsumerRepository consumerRepository){

        this.connectionFactory = connectionFactory;
        this.consumerRepository = consumerRepository;
    }


    public List<String> getMessages() {
        return consumerRepository.findAllMessages();
    }

    @PostConstruct
    public void queueListener() throws IOException, TimeoutException {
        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Mensagem recebida ->" + message);
            consumerRepository.addMessage(message);
        };
        channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });
    }
}
