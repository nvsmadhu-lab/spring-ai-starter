package com.learning.ai_starter.service.impl;

import com.learning.ai_starter.service.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    public AiServiceImpl(ChatClient.Builder builder) {
        this.chatClient = builder
                .defaultSystem("You are a helpful assistant. Be concise and clear.")
                .build();
    }

    @Override
    public String ask(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }

    @Override
    public String askWithRole(String systemPrompt, String question) {
        return chatClient.prompt()
                .system(systemPrompt)
                .user(question)
                .call()
                .content();
    }

    @Override
    public String reviewCode(String code) {
        return chatClient.prompt()
                .system("""
                    You are a senior Java code reviewer.
                    Review the code for: bugs, performance issues,\s
                    best practices, and security concerns.
                    Be specific and actionable.
                   \s""")
                .user("Review this code:\n\n" + code)
                .call()
                .content();
    }

    @Override
    public String summarize(String text) {
        return chatClient.prompt()
                .system("Summarize the given text in 3-5 bullet points. Be concise.")
                .user(text)
                .call()
                .content();
    }


}
