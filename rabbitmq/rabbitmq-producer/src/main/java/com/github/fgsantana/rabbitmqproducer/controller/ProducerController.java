package com.github.fgsantana.rabbitmqproducer.controller;

import com.github.fgsantana.rabbitmqproducer.service.ProducerService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;

@RestController
public class ProducerController {

    final ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/messages")
    public void sendMessage(@RequestBody final String message, @PathParam("exchange") final String exchange) throws Exception {
        producerService.sendMessage(message,exchange);
    }

}
