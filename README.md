# 🤖 Spring AI Starter

An AI-powered Spring Boot application built with Spring AI framework, 
demonstrating core AI engineering concepts for Java developers.

## 🛠️ Tech Stack

- **Java 17**
- **Spring Boot 3.5.x**
- **Spring AI 1.0.0**
- **Groq API** (LLM Provider)
- **Maven**

## ✨ Features

### Week 1 — Basic AI Endpoints
- Simple Q&A endpoint
- Code review endpoint
- Text summarization endpoint
- Custom role-based responses

### Week 2A — Structured Output
- Extract structured Java objects from AI responses
- Developer profile extraction
- Movie recommendations as typed objects
- Code review with scored results
- List of structured objects using ParameterizedTypeReference

### Week 2B — Streaming
- Real-time streaming responses using SSE (Server-Sent Events)
- Stream with custom system prompts
- Stream code explanations
- HTML demo page for visual streaming

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- Groq API Key (free at console.groq.com)

### Setup

1. Clone the repository
   
   git clone https://github.com/YOUR_USERNAME/spring-ai-starter.git
   cd spring-ai-starter

2. Add your API key in `application.properties`
   
   spring.ai.openai.api-key=your_groq_api_key_here
   spring.ai.openai.base-url=https://api.groq.com/openai/v1
   spring.ai.openai.chat.options.model=llama-3.3-70b-versatile

3. Run the application
   
   mvn spring-boot:run

4. Test the endpoints
   
   http://localhost:8080/api/ai/ask?question=What is Kafka?

## 📡 API Endpoints

### Basic AI
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/ai/ask?question=` | Simple Q&A |
| POST | `/api/ai/ask-with-role` | Q&A with custom role |
| POST | `/api/ai/review-code` | Code review |
| POST | `/api/ai/summarize` | Text summarization |

### Structured Output
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/structured/developer-profile` | Extract developer profile |
| POST | `/api/structured/recommend-movie` | Single movie recommendation |
| POST | `/api/structured/review-code` | Scored code review |
| POST | `/api/structured/recommend-movies` | List of recommendations |

### Streaming (SSE)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/stream/ask?question=` | Stream any question |
| POST | `/api/stream/ask-with-role` | Stream with custom role |
| POST | `/api/stream/explain-code` | Stream code explanation |

## 💡 Key Learnings

- Spring AI abstracts LLM providers — swap Groq/OpenAI/Anthropic 
  by changing only properties, zero code changes
- `.call()` for blocking responses, `.stream()` for real-time streaming
- `.entity(Class)` converts AI text responses to typed Java objects
- SSE (Server-Sent Events) for streaming AI responses to frontend

## 🗺️ Roadmap

- [x] Week 1 — Basic AI endpoints
- [x] Week 2 — Structured Output + Streaming
- [ ] Week 3 — Tools (AI calling Java methods)
- [ ] Week 4 — Memory (conversation history)
- [ ] Week 5 — RAG (AI over custom documents)
- [ ] Week 6 — Complete AI-powered application

## 📬 Connect

Built by N V S Madhumitra — Senior Java Developer transitioning to AI Engineering
LinkedIn: https://www.linkedin.com/in/nvsmadhumitra/
