package com.learning.ai_starter.controller;

import com.learning.ai_starter.dto.ApiResponse;
import com.learning.ai_starter.service.AiService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/ai/")
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    // Endpoint 1: Simple question
    // GET http://localhost:8080/api/ai/ask?question=What is microservices?
    @GetMapping("/ask")
    public ResponseEntity<ApiResponse<Map<String,String>>> ask(@RequestParam String question) {

        if (question == null || question.isBlank()) {
            throw new IllegalArgumentException("Question cannot be empty");
        }

            String answer = aiService.ask(question);
            return ResponseEntity.ok(ApiResponse.success(Map.of(
                    "question",question,
                    "answer",answer
            )));
    }

    // Endpoint 2: Ask with custom role
    // POST http://localhost:8080/api/ai/ask-with-role
    @PostMapping("/ask-with-role")
    public ResponseEntity<ApiResponse<Map<String,String>>> askWithRole(
            @RequestBody Map<String,String> request) {

        if ( null == request.get("systemPrompt") || request.get("systemPrompt").isBlank() ||
                null == request.get("question") || request.get("question").isBlank()
        ) {
            throw new IllegalArgumentException("SystemPrompt/Question cannot be empty");
        }

        String systemPrompt = request.get("systemPrompt");
        String question = request.get("question");
        String answer = aiService.askWithRole(systemPrompt,question);
        return ResponseEntity.ok(ApiResponse.success(Map.of(
                "answer", answer
        )));
    }

    // Endpoint 3: Code reviewer
    // POST http://localhost:8080/api/ai/review-code
    @PostMapping("/review-code")
    public ResponseEntity<ApiResponse<Map<String,String>>> reviewCode(
            @RequestBody Map<String,String> request){

        if ( null == request.get("code") || request.get("code").isBlank()
        ) {
            throw new IllegalArgumentException("Code cannot be empty");
        }

        String review = aiService.reviewCode(request.get("code"));
        return ResponseEntity.ok(ApiResponse.success(Map.of(
                "review", review
        )));
    }

    // Endpoint 4: Summarizer
    // POST http://localhost:8080/api/ai/summarize
    @PostMapping("/summarize")
    public ResponseEntity<ApiResponse<Map<String,String>>> summarize(
            @RequestBody Map<String,String> request){

        if ( null == request.get("text") || request.get("text").isBlank()
        ) {
            throw new IllegalArgumentException("text cannot be empty");
        }

        String summary = aiService.summarize(request.get("text"));
        return ResponseEntity.ok(ApiResponse.success(Map.of(
                "summary", summary
        )));
    }



}
