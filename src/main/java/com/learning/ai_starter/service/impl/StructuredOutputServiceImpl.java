package com.learning.ai_starter.service.impl;

import com.learning.ai_starter.exception.AiServiceException;
import com.learning.ai_starter.model.CodeReviewResult;
import com.learning.ai_starter.model.DeveloperProfile;
import com.learning.ai_starter.model.MovieList;
import com.learning.ai_starter.model.MovieRecommendation;
import com.learning.ai_starter.service.StructuredOutputService;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StructuredOutputServiceImpl implements StructuredOutputService {

    private final ChatClient chatClient;

    @Override
    public DeveloperProfile extractDeveloperProfile(String description) {
        try{
        return chatClient.prompt()
                .system("""
                        Extract developer information from the given text.
                        Be accurate and concise.
                        If experience is not mentioned, set it to 0.
                        """)
                .user(description)
                .call()
                .entity(DeveloperProfile.class);
        } catch (Exception e) {
            throw new AiServiceException("Failed to process query", e);
        }
    }

    @Override
    public MovieRecommendation recommendMovie(String genre, String mood) {
        try{
        return chatClient.prompt()
                .system("You are a movie expert. Recommend exactly one movie.")
                .user("Recommend a " + genre + " movie for someone who is " + mood)
                .call()
                .entity(MovieRecommendation.class);
        } catch (Exception e) {
            throw new AiServiceException("Failed to process query", e);
        }
    }

    @Override
    public CodeReviewResult reviewCode(String code) {
        try{
        return chatClient.prompt()
                .system("""
                        You are a senior Java code reviewer.
                        Analyze the code thoroughly.
                        Score it from 1-10.
                        List specific bugs, improvements, and good practices.
                        """)
                .user("Review this code:\n\n" + code)
                .call()
                .entity(CodeReviewResult.class);
        } catch (Exception e) {
            throw new AiServiceException("Failed to process query", e);
        }

    }

    @Override
    public List<MovieRecommendation> recommendMultipleMovies(String genre) {
        try{
        MovieList results =  chatClient.prompt()
                .system("You are a movie expert.")
                .user("Recommend 3 great " + genre + " movies")
                .call()
                .entity(MovieList.class);

        return results != null ? results.movies() : Collections.emptyList();
        } catch (Exception e) {
            throw new AiServiceException("Failed to process query", e);
        }
    }
}
