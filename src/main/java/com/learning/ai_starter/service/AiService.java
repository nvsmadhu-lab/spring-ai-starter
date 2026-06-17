package com.learning.ai_starter.service;

import org.springframework.stereotype.Service;

public interface AiService {

    // 1. Simple Q&A
    String ask(String question);

    // 2. Q&A with custom system role
    String askWithRole(String systemPrompt, String question);

    // 3. Code reviewer - practical example
    String reviewCode(String code);

    // 4. Summarizer - practical example
    String summarize(String text);


}
