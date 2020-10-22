package com.github.fgsantana.somemensagingproducer.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@RequiredArgsConstructor
public final class ProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;


    public void sendMessage(String message) {
        ListenableFuture<SendResult<String, String>> future = kafkaTemplate.send("topico1", message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onFailure(Throwable ex) {
                System.out.println("Failure!");
            }

            @Override
            public void onSuccess(SendResult<String, String> result) {
                System.out.println("Message sent!");
            }
        });

    }


}
