@Configuration
public class ProducerConfig{
     @Bean
public KafkaTemplate<String, String> stringTemplate(ProducerFactory<String, String> pf) {
    return new KafkaTemplate<>(pf);
}

@Bean
public KafkaTemplate<String, byte[]> bytesTemplate(ProducerFactory<String, byte[]> pf) {
    return new KafkaTemplate<>(pf,
            Collections.singletonMap(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class));
}

// Cocurrent message listeners
// @Bean
// public ConcurrentMessageListenerContainer<String, String> replyContainer(
//         ConcurrentKafkaListenerContainerFactory<String, String> containerFactory) {

//     ConcurrentMessageListenerContainer<String, String> container = containerFactory.createContainer("topic2");
//     container.getContainerProperties().setGroupId(UUID.randomUUID().toString()); // unique
//     Properties props = new Properties();
//     props.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest"); // so the new group doesn't get old replies
//     container.getContainerProperties().setKafkaConsumerProperties(props);
//     return container;
// }
}