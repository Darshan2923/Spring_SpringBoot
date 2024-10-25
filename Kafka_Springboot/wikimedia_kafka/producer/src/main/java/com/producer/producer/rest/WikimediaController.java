package main.java.com.producer.producer.rest;

import main.java.com.producer.producer.stream.WikimediaStreamConsumer;

@RestController
@RequestMapping("/api/v1/wikimedia")
@RequiredArgsConstructor
public class WikimediaController {

    private final WikimediaStreamConsumer streamConsumer;

    @GetMapping
    public void startPublishing() {
        streamConsumer.consumeStreamAndPublish();
    }
}
