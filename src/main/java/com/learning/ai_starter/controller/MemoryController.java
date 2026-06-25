package com.learning.ai_starter.controller;

import ch.qos.logback.core.net.SyslogOutputStream;
import com.learning.ai_starter.dto.ApiResponse;
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
    public ResponseEntity<ApiResponse<Map<String,String>>> newSession(){
        String sessionId = UUID.randomUUID().toString();
        return ResponseEntity.ok(ApiResponse.success(Map.of(
                "sessionId", sessionId,
                "message", "New session created! Use this sessionId in /chat"
        )));
    }

    // ✅ Chat with memory
    // POST http://localhost:8080/api/memory/chat
    @PostMapping(value = "/chat")
    public ResponseEntity<ApiResponse<Map<String,String>>> chat(
            @RequestBody ChatRequest request
    ){

        if ( null == request.message() || request.message().isBlank()
        ) {
            throw new IllegalArgumentException("text cannot be empty");
        }

        String sessionId = request.sessionId() != null
                ? request.sessionId()
                : UUID.randomUUID().toString();

        String response = memoryService.chat(sessionId, request.message());

        return ResponseEntity.ok(ApiResponse.success(Map.of(
                "sessionId", sessionId,
                "message", request.message(),
                "response", response
        )));
    }

}
