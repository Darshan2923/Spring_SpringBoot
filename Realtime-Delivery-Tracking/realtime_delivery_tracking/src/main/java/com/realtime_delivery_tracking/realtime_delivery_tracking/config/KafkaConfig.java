package com.realtime_delivery_tracking.realtime_delivery_tracking.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import com.realtime_delivery_tracking.realtime_delivery_tracking.constants.AppConstant;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name(AppConstant.DELIVERY_LOCATION).build();
    }
}
