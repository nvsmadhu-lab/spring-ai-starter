package com.learning.ai_starter.service.impl;

import com.learning.ai_starter.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
@RequiredArgsConstructor
public class StreamingServiceImpl implements StreamingService {

    private final ChatClient chatClient;

    @Override
    public Flux<String> streamResponse(String question) {
        return chatClient.prompt()
                .user(question)
                .stream().content();
    }

    @Override
    public Flux<String> streamWithRole(String systemPrompt, String question) {
        return chatClient.prompt()
                .system(systemPrompt)
                .user(question)
                .stream().content();
    }

    @Override
    public Flux<String> streamCodeExplanation(String code) {
        return chatClient.prompt()
                .system("""
                        You are a senior Java developer.
                        Explain the given code in detail.
                        Cover: what it does, how it works, 
                        potential issues, and improvements.
                        """)
                .user("Explain this code:\n\n" + code)
                .stream().content();
    }

    @Override
    public Flux<String> streamWithPrefix(String question) {
        return chatClient.prompt()
                .user(question)
                .stream().content()
                .map(chunk -> chunk);
    }
}
