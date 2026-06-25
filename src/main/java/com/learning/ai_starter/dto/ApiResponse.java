package com.learning.ai_starter.dto;

import java.time.LocalDateTime;

public record ApiResponse<T>(boolean success,
                             T data,
                             String error,
                             LocalDateTime timestamp) {

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>(true, data, null, LocalDateTime.now());
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>(false, null, message, LocalDateTime.now());
    }

}
