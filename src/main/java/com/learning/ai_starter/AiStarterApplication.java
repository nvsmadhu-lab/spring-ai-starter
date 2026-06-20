package com.learning.ai_starter;

import org.springframework.ai.model.ollama.autoconfigure.OllamaChatAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(exclude = {
        OllamaChatAutoConfiguration.class  // ✅ Disable Ollama chat - keep only embeddings
})
public class AiStarterApplication {

	public static void main(String[] args) {
		SpringApplication.run(AiStarterApplication.class, args);
	}

}
