package com.github.fgsantana.somemensagingconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerService {
    List<String> list = new ArrayList<String>();

    @KafkaListener(topics = "topico1")
    public void consume(@Payload String message) {
        list.add(message);
        System.out.println(message);

    }

    public List<String> getAllMessages() {
        return list;
    }
}
