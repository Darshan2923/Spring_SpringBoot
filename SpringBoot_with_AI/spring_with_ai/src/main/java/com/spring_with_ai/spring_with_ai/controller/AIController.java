package com.spring_with_ai.spring_with_ai.controller;

import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class AIController {

    // private static final String PROMPT = "what is java language";

    private OllamaChatModel chatModel;

    public AIController(OllamaChatModel chatModel) {
        this.chatModel = chatModel;
    }

    // @GetMapping("/prompt")
    // public Flux<String> promptResponse(
    // @RequestParam("prompt") String prompt) {
    // Flux<String> response = chatModel.stream(prompt);
    // return response;
    // }

    @GetMapping("/prompt")
    public Flux<ChatResponse> promptResponse(
            @RequestParam("prompt") String prompt) {
        Prompt promptOb = new Prompt(prompt);
        Flux<ChatResponse> response = chatModel.stream(promptOb);
        return response;
    }

}
