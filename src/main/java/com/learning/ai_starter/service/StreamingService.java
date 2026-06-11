package com.learning.ai_starter.service;

import reactor.core.publisher.Flux;

public interface StreamingService {

    // Basic streaming - returns Flux of String chunks
    public Flux<String> streamResponse(String question);

    // Streaming with system prompt
    public Flux<String> streamWithRole(String systemPrompt, String question);

    // Streaming code explanation - great for long responses
    public Flux<String> streamCodeExplanation(String code);

    // Streaming with word count - shows transforming Flux
    public Flux<String> streamWithPrefix(String question);



}
