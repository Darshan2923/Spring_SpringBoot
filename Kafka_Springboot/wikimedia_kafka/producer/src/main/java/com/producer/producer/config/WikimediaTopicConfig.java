package main.java.com.producer.producer.config;

@Configuration
public class WikimediaTopicConfig {
    @Bean
    public NewTopic wikimediaStreamTopic() {
        return TopicBuilder
                .name("wikimedia-stream")
                .build();

    }
}
