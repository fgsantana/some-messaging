package com.github.fgsantana.somemensagingproducer.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.github.fgsantana.somemensagingproducer.service.ProducerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ProducerController {

    private final ProducerService service;

    @PostMapping("/sendMessage")
    public void sendMsg(@RequestBody String message) throws JsonProcessingException {
        service.sendMessage(message);

    }
}
