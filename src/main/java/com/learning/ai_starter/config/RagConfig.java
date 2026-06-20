package com.learning.ai_starter.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.vectorstore.QuestionAnswerAdvisor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RagConfig {

    @Bean
    public VectorStore vectorStore(@Qualifier("ollamaEmbeddingModel") EmbeddingModel embeddingModel){
        return SimpleVectorStore.builder(embeddingModel).build();
    }

    @Bean
    public ChatClient ragChatClient(
            @Qualifier("openAiChatModel") ChatModel chatModel,
            VectorStore vectorStore
    ){
        return ChatClient.builder(chatModel)
                .defaultSystem("""
                        You are a helpful assistant that answers questions
                        based ONLY on the provided document context.
                        If the answer is not in the documents, say:
                        "I couldn't find that information in the documents."
                        Never make up information.
                        Always cite which document you got the answer from.
                        """)
                .defaultAdvisors(QuestionAnswerAdvisor.builder(vectorStore).build())
                .build();
    }
}
