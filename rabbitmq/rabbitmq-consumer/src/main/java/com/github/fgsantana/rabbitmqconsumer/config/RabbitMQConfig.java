package com.github.fgsantana.rabbitmqconsumer.config;

import com.rabbitmq.client.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Bean
    public ConnectionFactory connectionFactory(){
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("0.0.0.0");
        return factory;
    }
}
