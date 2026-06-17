package com.learning.ai_starter.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.learning.ai_starter.model.ChatRequest;
import com.learning.ai_starter.service.MemoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping( "/api/memory")
public class MemoryController {

    private final MemoryService memoryService;

    // ✅ Start a new conversation session
    // GET http://localhost:8080/api/memory/new-session
    @GetMapping(value = "/new-session")
    public ResponseEntity<Map<String,String>> newSession(){
        String sessionId = UUID.randomUUID().toString();
        return ResponseEntity.ok(Map.of(
                "sessionId", sessionId,
                "message", "New session created! Use this sessionId in /chat"
        ));
    }

    // ✅ Chat with memory
    // POST http://localhost:8080/api/memory/chat
    @PostMapping(value = "/chat")
    public ResponseEntity<Map<String,String>> chat(
            @RequestBody ChatRequest request
    ){
        String sessionId = request.sessionId() != null
                ? request.sessionId()
                : UUID.randomUUID().toString();

        System.out.println(sessionId);

        String response = memoryService.chat(sessionId, request.message());

        return ResponseEntity.ok(Map.of(
                "sessionId", sessionId,
                "message", request.message(),
                "response", response
        ));
    }

}
