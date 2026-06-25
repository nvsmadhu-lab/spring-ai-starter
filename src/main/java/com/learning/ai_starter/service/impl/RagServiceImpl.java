package com.learning.ai_starter.service.impl;

import com.learning.ai_starter.exception.AiServiceException;
import com.learning.ai_starter.service.RagService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.pdf.PagePdfDocumentReader;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class RagServiceImpl implements RagService {

    private final ChatClient ragChatClient;
    private final VectorStore vectorStore;

    public RagServiceImpl(
            @Qualifier("ragChatClient") ChatClient ragChatClient,
            VectorStore vectorStore) {
        this.ragChatClient = ragChatClient;
        this.vectorStore = vectorStore;
    }

    // ✅ Upload and index a PDF
    @Override
    public String uploadDocument(MultipartFile file) {
        try {
            // 1. Read PDF
            Resource resource = file.getResource();
            PagePdfDocumentReader pdfReader =
                    new PagePdfDocumentReader(resource);
            List<Document> documents = pdfReader.get();

            // 2. Split into chunks
            TokenTextSplitter splitter = TokenTextSplitter.builder().build();
            List<Document> chunks = splitter.apply(documents);

            // 3. Store in vector database
            vectorStore.add(chunks);

            return String.format(
                    "Successfully indexed %d chunks from %s",
                    chunks.size(), file.getOriginalFilename()
            );
        } catch (Exception e) {
            throw new AiServiceException( "Failed to index document: " , e);
        }
    }

    @Override
    public String askQuestion(String question) {
        try {
        return ragChatClient.prompt()
                .user(question)
                .call()
                .content();
        // QuestionAnswerAdvisor automatically:
        // 1. Converts question to vector
        // 2. Searches vector DB for similar chunks
        // 3. Adds relevant chunks to prompt
        // 4. LLM answers using those chunks
        } catch (Exception e) {
            throw new AiServiceException("Failed to process RAG query", e);
        }
    }

    @Override
    public String loadDocumentFromClasspath(@Value("classpath:documents/company-policy.pdf") Resource resource) {
        try {
            PagePdfDocumentReader pdfReader =
                    new PagePdfDocumentReader(resource);
            List<Document> documents = pdfReader.get();
            TokenTextSplitter splitter = TokenTextSplitter.builder().build()    ;
            List<Document> chunks = splitter.apply(documents);
            vectorStore.add(chunks);
            return "Loaded " + chunks.size() + " chunks from classpath";
        } catch (Exception e) {
            throw new AiServiceException( "Failed to load document: " , e);
        }
    }
}
