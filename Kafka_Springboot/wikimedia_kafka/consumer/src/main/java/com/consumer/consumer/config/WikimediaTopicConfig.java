package main.java.com.consumer.consumer.config;

@Configuration
public class WikimediaTopicConfig {
    @Bean
    public NewTopic wikimediaStreamTopic() {
        return TopicBuilder
                .name("wikimedia-stream")
                .build();

    }
}
