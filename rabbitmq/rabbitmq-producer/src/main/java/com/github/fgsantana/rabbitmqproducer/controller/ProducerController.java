package com.github.fgsantana.rabbitmqproducer.controller;

import com.github.fgsantana.rabbitmqproducer.service.ProducerService;
import org.springframework.http.RequestEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

@RestController
public class ProducerController {

    ProducerService producerService;

    public ProducerController(ProducerService producerService) {
        this.producerService = producerService;
    }

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestBody String message, @PathParam("exchange") String exchange) throws Exception {
        producerService.sendMessage(message,exchange);
    }

}
