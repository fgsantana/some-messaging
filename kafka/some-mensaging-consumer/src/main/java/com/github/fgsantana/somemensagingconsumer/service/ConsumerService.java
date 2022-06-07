package com.github.fgsantana.somemensagingconsumer.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ConsumerService {
    List<String> list = new ArrayList<>();

    @KafkaListener(topics = "test-topic", groupId = "groupId")
    public void consume(@Payload String data) {
        list.add(data);
    }

    public List<String> getAllMessages() {
        return list;
    }
}
