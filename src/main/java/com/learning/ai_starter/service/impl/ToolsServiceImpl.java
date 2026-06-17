package com.learning.ai_starter.service.impl;

import com.learning.ai_starter.service.ToolsService;
import com.learning.ai_starter.tools.DatabaseTools;
import com.learning.ai_starter.tools.DateTimeTools;
import com.learning.ai_starter.tools.MathTools;
import com.learning.ai_starter.tools.WeatherTools;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToolsServiceImpl implements ToolsService {

    private final ChatClient chatClient;
    private final DatabaseTools databaseTools;
    private final WeatherTools weatherTools;
    private final MathTools mathTools;
    private final DateTimeTools dateTimeTools;


    @Override
    public String chatWithTools(String question) {
        return chatClient.prompt()
                .system("""
                    You are a helpful assistant with access to several tools:
                    - Weather tools for current weather and forecasts
                    - Math tools for calculations
                    - DateTime tools for dates and times
                    - Database tools for product and order lookups
                    
                    IMPORTANT: Always use the available tools to get accurate, 
                    real-time data. Never guess or make up information that 
                    a tool can provide. Call the appropriate tool(s) based 
                    on the user's question.
                    """)
                .user(question)
                .tools(
                        weatherTools,
                        databaseTools,
                        dateTimeTools,
                        mathTools
                )
                .call()
                .content();
    }

    @Override
    public String chatWithWeatherTools(String question) {
        return chatClient.prompt()
                .system("You are a weather assistant.")
                .user(question)
                .tools(
                        weatherTools
                )
                .call()
                .content();
    }
}
