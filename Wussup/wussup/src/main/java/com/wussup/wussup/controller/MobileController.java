package com.wussup.wussup.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wussup.wussup.service.KafkaService;

@RestController
@RequestMapping("/mobile")
public class MobileController {

    @Autowired
    private KafkaService kafkaService;

    @PostMapping("/sendMessage")
    public void sendMessage(@RequestParam String key, @RequestParam String senderName,
            @RequestParam String recipientNumber, @RequestParam String bodyMessage) {
        kafkaService.sendMessage(key, senderName, recipientNumber, bodyMessage);
    }

}
