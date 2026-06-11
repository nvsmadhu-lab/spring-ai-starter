package com.learning.ai_starter.model;

import java.util.List;

public record MovieRecommendation(
        String title,
        String genre,
        int releaseYear,
        double rating,
        String reason,
        List<String> similarMovies
) {}
