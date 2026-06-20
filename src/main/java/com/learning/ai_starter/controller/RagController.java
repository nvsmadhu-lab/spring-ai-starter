package com.learning.ai_starter.controller;

import com.learning.ai_starter.service.RagService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/rag")
public class RagController {

    private final RagService ragService;

    // Upload PDF to vector store
    // POST http://localhost:8080/api/rag/upload
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
        public ResponseEntity<Map<String,String>> uploadDocument(
                @RequestParam("file") MultipartFile file
                ){
        String result = ragService.uploadDocument(file);
        return ResponseEntity.ok(Map.of("result", result));
    }

    // Ask question about uploaded documents
    // POST http://localhost:8080/api/rag/ask
    @PostMapping(
            value = "/ask"
    )
    public ResponseEntity<Map<String,String>> ask(
            @RequestBody Map<String, String> request
    ){
        String response = ragService.askQuestion(request.get("question"));
        return ResponseEntity.ok(Map.of(
                "question", request.get("question"),
                "response", response
        ));
    }
}
