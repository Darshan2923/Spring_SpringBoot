package com.wussup.wussup.configuration;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

    /*
     * @Value(value="${spring.kafka.bootstrap-servers}")
     * private String bootstrapAddress;
     */
    @Value(value = "${message.topic.private.name}")
    private String privateTopicName;

    /*
     * @Bean
     * public KafkaAdmin kafkaAdmin(){
     * Map<String,Object> configs=new HashMap<>();
     * configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG,bootstrapAddress);
     * return new KafkaAdmin(configs);
     * }
     */

    @Bean
    public NewTopic privateTopic() {
        return TopicBuilder.name(privateTopicName)
                .partitions(10)
                .replicas(1)
                .compact()
                .build();
    }
}
