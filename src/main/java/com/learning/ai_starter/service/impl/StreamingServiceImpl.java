package com.learning.ai_starter.service.impl;

import com.learning.ai_starter.exception.AiServiceException;
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
        try {
        return chatClient.prompt()
                .user(question)
                .stream().content();
        } catch (Exception e) {
            throw new AiServiceException("Failed to process query", e);
        }
    }

    @Override
    public Flux<String> streamWithRole(String systemPrompt, String question) {
        try{
        return chatClient.prompt()
                .system(systemPrompt)
                .user(question)
                .stream().content();
        } catch (Exception e) {
            throw new AiServiceException("Failed to process query", e);
        }
    }

    @Override
    public Flux<String> streamCodeExplanation(String code) {
        try{
        return chatClient.prompt()
                .system("""
                        You are a senior Java developer.
                        Explain the given code in detail.
                        Cover: what it does, how it works, 
                        potential issues, and improvements.
                        """)
                .user("Explain this code:\n\n" + code)
                .stream().content();
        } catch (Exception e) {
            throw new AiServiceException("Failed to process query", e);
        }
    }

    @Override
    public Flux<String> streamWithPrefix(String question) {
        try{
        return chatClient.prompt()
                .user(question)
                .stream().content()
                .map(chunk -> chunk);
        } catch (Exception e) {
            throw new AiServiceException("Failed to process query", e);
        }
    }
}
