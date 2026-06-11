package com.learning.ai_starter.model;

import java.util.List;

public record CodeReviewResult(
        String overallQuality,      // GOOD / AVERAGE / POOR
        int score,                  // 1-10
        List<String> bugs,
        List<String> improvements,
        List<String> goodPractices,
        String summary
) {}
