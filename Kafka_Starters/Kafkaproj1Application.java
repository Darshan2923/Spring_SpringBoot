package com.kafkaproj1.kafkaproj1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Kafkaproj1Application {

	public static void main(String[] args) {
		SpringApplication.run(Kafkaproj1Application.class, args);
	}


// Consumer App
	@Bean 
	public NewTopic topic(){
		return TopicBuilder.name("topic1")
				.partitions(10)
				.replicas(1)
				.build();
	}

	@KafkaListener(id = "myId", topics = "topic1")
    public void listen(String in) {
        System.out.println(in);
    }

// Producer App
 @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
            template.send("topic1", "test");
        };
    }

// You can also configure topics by defining KafkaAdmin bean to auto add topics to broker
// replicas and partitions are optional
@Bean
public KafkaAdmin.NewTopics topics456() {
    return new NewTopics(
            TopicBuilder.name("defaultBoth")
                .build(),
            TopicBuilder.name("defaultPart")
                .replicas(1)
                .build(),
            TopicBuilder.name("defaultRepl")
                .partitions(3)
                .build());
}

// Routing template to select producers dynamically
// @Bean
//     public RoutingKafkaTemplate routingTemplate(GenericApplicationContext context,
//             ProducerFactory<Object, Object> pf) {

//         // Clone the PF with a different Serializer, register with Spring for shutdown
//         Map<String, Object> configs = new HashMap<>(pf.getConfigurationProperties());
//         configs.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class);
//         DefaultKafkaProducerFactory<Object, Object> bytesPF = new DefaultKafkaProducerFactory<>(configs);
//         context.registerBean("bytesPF", DefaultKafkaProducerFactory.class, () -> bytesPF);

//         Map<Pattern, ProducerFactory<Object, Object>> map = new LinkedHashMap<>();
//         map.put(Pattern.compile("two"), bytesPF);
//         map.put(Pattern.compile(".+"), pf); // Default PF with StringSerializer
//         return new RoutingKafkaTemplate(map);
//     }

//     @Bean
//     public ApplicationRunner runner(RoutingKafkaTemplate routingTemplate) {
//         return args -> {
//             routingTemplate.send("one", "thing1");
//             routingTemplate.send("two", "thing2".getBytes());
//         };
//     }

// Replyng using Boot's default config reply containers
// @SpringBootApplication
// public class KReplyingApplication {

//     public static void main(String[] args) {
//         SpringApplication.run(KReplyingApplication.class, args);
//     }

//     @KafkaListener(id="server", topics = "kRequests")
//     @SendTo // use default replyTo expression
//     public String listen(String in) {
//         System.out.println("Server received: " + in);
//         return in.toUpperCase();
//     }

//     @Bean
//     public NewTopic kRequests() {
//         return TopicBuilder.name("kRequests")
//             .partitions(10)
//             .replicas(2)
//             .build();
//     }

//     @Bean // not required if Jackson is on the classpath
//     public MessagingMessageConverter simpleMapperConverter() {
//         MessagingMessageConverter messagingMessageConverter = new MessagingMessageConverter();
//         messagingMessageConverter.setHeaderMapper(new SimpleKafkaHeaderMapper());
//         return messagingMessageConverter;
//     }

// }
}
