package com.learning.ai_starter.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class ChatConfig {

    @Bean
    @Primary
    public ChatClient chatClient(@Qualifier("openAiChatModel")ChatModel chatModel) {
        return ChatClient.builder(chatModel)
                .defaultSystem("You are a helpful assistant.")
                .build();
    }

//    @Bean
//    public ChatMemory chatMemory() {
//        // ✅ MessageWindowChatMemory replaces InMemoryChatMemory in 1.0.0
//        return MessageWindowChatMemory.builder()
//                .maxMessages(20)  // remembers last 20 messages
//                .build();
//    }
//
//    // ChatClient with memory for chat conversations
//    @Bean
//    public ChatClient chatClientWithMemory(ChatModel chatModel, ChatMemory chatMemory) {
//        return ChatClient.builder(chatModel)
//                .defaultSystem("""
//                        You are a helpful assistant.
//                        Remember everything the user tells you.
//                        """)
//                .defaultAdvisors(
//                        MessageChatMemoryAdvisor.builder(chatMemory).build()
//                )
//                .build();
//    }


}
