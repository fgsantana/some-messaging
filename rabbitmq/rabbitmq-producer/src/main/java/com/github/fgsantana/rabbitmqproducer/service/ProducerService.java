package com.github.fgsantana.rabbitmqproducer.service;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.stereotype.Service;


@Service
public class ProducerService {

    final ConnectionFactory connectionFactory;

    public ProducerService(ConnectionFactory connectionFactory){

        this.connectionFactory = connectionFactory;
    }

    public void sendMessage(final String message, final String exchange) throws Exception {
        try (Connection connection = connectionFactory.newConnection();
             final Channel channel = connection.createChannel()){
            channel.basicPublish(exchange,"",null,message.getBytes());
        }

    }




}
