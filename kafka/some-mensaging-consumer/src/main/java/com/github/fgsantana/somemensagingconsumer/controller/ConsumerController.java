package com.github.fgsantana.somemensagingconsumer.controller;

import com.github.fgsantana.somemensagingconsumer.service.ConsumerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService service;

    @GetMapping("/messages")
    public List<String> getMessages() {
        return service.getAllMessages();
    }

}