package com.learning.ai_starter.controller;

import com.learning.ai_starter.model.CodeReviewResult;
import com.learning.ai_starter.model.DeveloperProfile;
import com.learning.ai_starter.model.MovieRecommendation;
import com.learning.ai_starter.service.StructuredOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/structured")
@RequiredArgsConstructor
public class StructuredOutputController {

    private final StructuredOutputService structuredOutputService;

    // POST http://localhost:8080/api/structured/developer-profile
    // Body: { "description": "Mahesh is a Java developer with 6.8 years..." }
    @PostMapping("/developer-profile")
    public ResponseEntity<DeveloperProfile> extractProfile(
            @RequestBody Map<String, String> request) {
        DeveloperProfile profile = structuredOutputService.extractDeveloperProfile(
                request.get("description")
        );
        return ResponseEntity.ok(profile);
    }


    // POST http://localhost:8080/api/structured/recommend-movie
    // Body: { "genre": "sci-fi", "mood": "adventurous" }
    @PostMapping("/recommend-movie")
    public ResponseEntity<MovieRecommendation> recommendMovie(
            @RequestBody Map<String, String > request){
        MovieRecommendation movies = structuredOutputService.recommendMovie(
                request.get("genre"),request.get("mood")
        );

        return ResponseEntity.ok(movies);
    }

    // POST http://localhost:8080/api/structured/review-code
    // Body: { "code": "public void save(User u) { repo.save(u); }" }
    @PostMapping("/review-code")
    public ResponseEntity<CodeReviewResult> reviewCode(
            @RequestBody Map<String, String>    request){
        CodeReviewResult review = structuredOutputService.reviewCode(
                request.get("code")
        );

        return ResponseEntity.ok(review);
    }


    // POST http://localhost:8080/api/structured/recommend-movies
    // Body: { "genre": "thriller" }
    @PostMapping("/recommend-movies")
    public ResponseEntity<List<MovieRecommendation>> recommendMovies(
            @RequestBody Map<String, String> request){
        List<MovieRecommendation> movies = structuredOutputService.recommendMultipleMovies(
                request.get("genre")
        );

        return ResponseEntity.ok(movies);
    }

}
