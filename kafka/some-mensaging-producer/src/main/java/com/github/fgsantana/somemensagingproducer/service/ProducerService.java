package com.github.fgsantana.somemensagingproducer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
public final class ProducerService {
    private static final Logger logger = LoggerFactory.getLogger(ProducerService.class);

     final KafkaTemplate<String, String> kafkaTemplate;

    public ProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    private final String topic="test-topic";

    public void sendMessage(String key, String message) {
        ListenableFuture<SendResult<String, String>> future;

        if(key==null){
            future = kafkaTemplate.send(topic, message);
        }
        else{
            future = kafkaTemplate.send(topic, key, message);
        }

        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("Message sent!" );
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Failure!");
            }
        });

    }


}
