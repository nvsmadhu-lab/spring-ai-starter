package com.learning.ai_starter.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface RagService {

    String uploadDocument(MultipartFile file);

    String askQuestion(String question);

    String loadDocumentFromClasspath(@Value("classpath:documents/company-policy.pdf") Resource resource);
}
