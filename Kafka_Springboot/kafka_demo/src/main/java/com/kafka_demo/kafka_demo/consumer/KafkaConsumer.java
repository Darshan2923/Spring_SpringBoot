package com.kafka_demo.kafka_demo.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.kafka_demo.kafka_demo.payload.Student;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class KafkaConsumer {

    // @KafkaListener(topics = "darshan", groupId = "myGroup")
    public void consumeMsg(String msg) {
        log.info(String.format("Consuming the message from darshan Topic:: %s", msg));
    }

    @KafkaListener(topics = "darshan", groupId = "myGroup")
    public void consumeJsonMsg(Student student) {
        log.info(String.format("Consuming the message from darshan Topic:: %s", student.toString()));
    }
}
