package main.java.com.consumer.consumer.consumer;

@Service
@Slf4j
public class WikimediaConsumer {
    @KafkaListener(topics = "wikimedia-stream", groupId = "myGroup")
    public void consumeMsg(String msg) {
        log.info(String.format("Consuming the message from wikimedia-stream Topic:: %s", msg));
        // feel free to do anything with the data
    }
}
