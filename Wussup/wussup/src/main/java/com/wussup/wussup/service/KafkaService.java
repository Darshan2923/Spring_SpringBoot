package com.wussup.wussup.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.wussup.wussup.model.Message;

@Service
public class KafkaService {

    @Autowired
    private KafkaTemplate<String, Message> kafkaTemplate;

    @Value(value = "${message.topic.private.name}")
    private String privateTopicName;

    public void sendMessage(String key, String senderNumber, String recipientNumber, String bodyMessage) {
        kafkaTemplate.send(privateTopicName, key, Message.builder()
                .senderNumber(senderNumber)
                .recipientNumber(recipientNumber)
                .body(bodyMessage)
                .build());
    }

    @KafkaListener(topics = "${message.topic.private.name}", containerFactory = "mobileContainerFactory")
    public void listenerMobile(Message message) {
        System.out.println(message.getSenderNumber() + ": " + message.getBody());
    }
}