package com.learning.ai_starter.controller;

import com.learning.ai_starter.service.ToolsService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tools")
public class ToolsController {


    private final ToolsService toolsService;

    // All tools available
    // POST http://localhost:8080/api/tools/chat
    @PostMapping(value = "/chat")
    public ResponseEntity<Map<String,String>> chat(
            @NotNull @RequestBody Map<String,String> request
    ){
        String response = toolsService.chatWithTools(request.get("question"));
        return ResponseEntity.ok(
                Map.of(
                        "response", response
                )
        );
    }

    // Weather only
    // POST http://localhost:8080/api/tools/weather
    @PostMapping(value = "/weather")
    public ResponseEntity<Map<String,String>> weather(
            @RequestBody Map<String,String> request
    ){
        String response = toolsService.chatWithWeatherTools(request.get("question"));
        return ResponseEntity.ok(
                Map.of(
                        "question",request.get("question"),
                        "response", response
                )
        );
    }

}
