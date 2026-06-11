package com.learning.ai_starter.service;

import com.learning.ai_starter.model.CodeReviewResult;
import com.learning.ai_starter.model.DeveloperProfile;
import com.learning.ai_starter.model.MovieRecommendation;

import java.util.List;

public interface StructuredOutputService {

    // Example 1: Extract developer profile from plain text
    public DeveloperProfile extractDeveloperProfile(String description);

    // Example 2: Get movie recommendation as object
    public MovieRecommendation recommendMovie(String genre, String mood);

    // Example 3: Review code and return structured result
    public CodeReviewResult reviewCode(String code);

    // Example 4: Return a List of objects
    public List<MovieRecommendation> recommendMultipleMovies(String genre);

}
