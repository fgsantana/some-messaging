package com.github.fgsantana.rabbitmqconsumer.controller;

import com.github.fgsantana.rabbitmqconsumer.service.ConsumerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConsumerController {

    final ConsumerService consumerService;

    public ConsumerController(ConsumerService consumerService) {
        this.consumerService = consumerService;
    }

    @GetMapping("/messages")
    public List<String> getMessages(){
        return consumerService.getMessages();
    }
}
