package com.github.fgsantana.somemensagingproducer.controller;


import com.github.fgsantana.somemensagingproducer.service.ProducerService;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    private final ProducerService service;

    public ProducerController(ProducerService service) {
        this.service = service;
    }

    @PostMapping("/sendMessage")
    public void sendMsg(@RequestBody String message, @Nullable @RequestParam("key") String key){
        service.sendMessage(key,message);

    }
}
