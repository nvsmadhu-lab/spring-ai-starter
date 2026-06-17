package com.learning.ai_starter.service.impl;

import com.learning.ai_starter.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class MemoryServiceImpl implements MemoryService {

    private final ChatClient chatClientWithMemory;

    public MemoryServiceImpl(
            @Qualifier("chatClientWithMemory") ChatClient chatClientWithMemory) {
        this.chatClientWithMemory = chatClientWithMemory;
    }

    @Override
    public String chat(String sessionId, String message) {
        return chatClientWithMemory.prompt()
                .user(message)
                .advisors(advisor -> advisor
                        .param("chat_memory_conversation_id", sessionId)
                        )
                .call()
                .content();
    }
}
