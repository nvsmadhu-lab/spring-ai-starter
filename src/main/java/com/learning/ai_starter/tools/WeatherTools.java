package com.learning.ai_starter.tools;

import org.jetbrains.annotations.NotNull;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class WeatherTools {

    @Tool(description = "Get current weather for a given city"+
                        "Call this when user asks about weather of any city/State/country.")
    public String getWeather(@NotNull String city){
        // In real project: call actual weather API here
        // For now returning mock data
        return switch (city.toLowerCase()){
            case "bangalore", "bengaluru" ->
                    "28°C, Partly Cloudy, Humidity: 65%";
            case "mumbai" ->
                "32°C, Humid, Humidity: 80%";
            case "dehli" ->
                "35°C, Sunny, Humidity: 40%";
            case "chennai" ->
                "33°C, Hot and Humid, Humidity: 75%";
            default ->
                "27°C, Clear Sky, Humidity: 55%";
        };
    }

    @Tool(description = "Get 3-day weather forecast for a city")
    public String getWeatherForecast(String city){
        return String.format("""
                3-Day Forecast for %s:
                Day 1: 28°C, Sunny
                Day 2: 26°C, Light Rain
                Day 3: 29°C, Partly Cloudy
                """, city);
    }
}
