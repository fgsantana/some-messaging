package com.github.fgsantana.rabbitmqconsumer.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ConsumerRepository {

    List<String> messages = new ArrayList<>();

    public List<String> findAllMessages(){
        return messages;
    }

    public void addMessage(final String message){
        messages.add(message);
    }
}
