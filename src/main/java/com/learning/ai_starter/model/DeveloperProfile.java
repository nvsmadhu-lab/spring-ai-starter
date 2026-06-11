package com.learning.ai_starter.model;

import java.util.List;

public record DeveloperProfile(
        String name,
        String role,
        double experienceYears,
        List<String> skills,
        String summary
) {}
