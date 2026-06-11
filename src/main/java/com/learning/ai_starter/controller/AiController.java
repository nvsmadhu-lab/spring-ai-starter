package com.learning.ai_starter.controller;

import com.learning.ai_starter.service.AiService;
import com.learning.ai_starter.service.impl.AiServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Map<String,String>> ask(@RequestParam String question) {
            String answer = aiService.ask(question);
            return ResponseEntity.ok(Map.of(
                    "question",question,
                    "answer",answer
            ));
    }

    // Endpoint 2: Ask with custom role
    // POST http://localhost:8080/api/ai/ask-with-role
    @PostMapping("/ask-with-role")
    public ResponseEntity<Map<String,String>> askWithRole(
            @RequestBody Map<String,String> request) {
        String systemPrompt = request.get("systemPrompt");
        String question = request.get("question");
        String answer = aiService.askWithRole(systemPrompt,question);
        return ResponseEntity.ok(Map.of(
                "answer", answer
        ));
    }

    // Endpoint 3: Code reviewer
    // POST http://localhost:8080/api/ai/review-code
    @PostMapping("/review-code")
    public ResponseEntity<Map<String,String>> reviewCode(
            @RequestBody Map<String,String> request){
        String review = aiService.reviewCode(request.get("code"));
        return ResponseEntity.ok(Map.of(
                "review", review
        ));
    }

    // Endpoint 4: Summarizer
    // POST http://localhost:8080/api/ai/summarize
    @PostMapping("/summarize")
    public ResponseEntity<Map<String,String>> summarize(
            @RequestBody Map<String,String> request){
        String summary = aiService.summarize(request.get("text"));
        return ResponseEntity.ok(Map.of(
                "summary", summary
        ));
    }



}
