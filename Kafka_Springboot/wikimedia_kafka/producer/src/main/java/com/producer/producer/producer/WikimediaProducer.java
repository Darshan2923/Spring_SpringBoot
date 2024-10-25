package main.java.com.producer.producer.producer;

@Service
@RequiredArgsConstructor
@Slf4j
public class WikimediaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String msg) {
        // log.info(String.format("Sending message to Darshan", msg));
        kafkaTemplate.send("wikimedia-stream", msg);
    }
}
