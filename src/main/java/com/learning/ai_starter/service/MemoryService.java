package com.learning.ai_starter.service;

public interface MemoryService {

    // ✅ Core method - chat with memory per sessionId
    String chat(String sessionId, String message);
}
