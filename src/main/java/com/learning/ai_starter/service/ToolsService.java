package com.learning.ai_starter.service;

public interface ToolsService {

    // All tools available - AI picks which one to use
     String chatWithTools(String question);

    // Only weather tools
     String chatWithWeatherTools(String question);
}
