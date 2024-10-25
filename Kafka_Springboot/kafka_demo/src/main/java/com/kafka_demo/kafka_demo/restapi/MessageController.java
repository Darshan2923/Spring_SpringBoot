package com.kafka_demo.kafka_demo.restapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kafka_demo.kafka_demo.payload.Student;
import com.kafka_demo.kafka_demo.producer.KafkaJsonProducer;
import com.kafka_demo.kafka_demo.producer.KafkaProducer;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/messages")
@RequiredArgsConstructor
public class MessageController {

    private final KafkaProducer kafkaProducer;
    private final KafkaJsonProducer kafkajsonProducer;

    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestBody String message) {
        kafkaProducer.sendMessage(message);
        return ResponseEntity.ok("Message queued successfully");
    }

    @PostMapping("/json")
    public ResponseEntity<String> sendMessage(
            @RequestBody Student message) {
        kafkajsonProducer.sendMessage(message);
        return ResponseEntity.ok("Message queued successfully as Json");
    }
}
