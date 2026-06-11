package com.learning.ai_starter.controller;

import com.learning.ai_starter.service.StreamingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stream")
@CrossOrigin(origins = "*")
public class StreamingController {

    private final StreamingService streamingService;

    // Endpoint 1 - Basic streaming
    // GET http://localhost:8080/api/stream/ask?question=Explain Kafka
    @GetMapping(
            value="/ask",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> streamAsk(@RequestParam String question){
        return streamingService.streamResponse(question);
    }

    // Endpoint 2 - Stream with custom role
    // POST http://localhost:8080/api/stream/ask-with-role
    @PostMapping(
            value="/ask-with-role",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> streamWithRole(@RequestBody Map<String,String> request){
        return streamingService.streamWithRole(
                request.get("systemPrompt"),
                request.get("question")
        );
    }

    // Endpoint 3 - Stream code explanation
    // POST http://localhost:8080/api/stream/explain-code
    @PostMapping(
            value = "/explain-code",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> explainCode(@RequestParam String code){
        return streamingService.streamCodeExplanation(code);
    }


    // Endpoint 4 - Stream with prefix
    // GET http://localhost:8080/api/stream/ask-prefix
    @GetMapping(
            value = "/ask-prefix",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public Flux<String> streamPrefix(@RequestParam String question){
        return streamingService.streamWithPrefix(question);
    }
}
