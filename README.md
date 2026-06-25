# 🤖 Spring AI Starter

A hands-on AI engineering project built with **Spring AI + Spring Boot**, covering core GenAI concepts from scratch — designed by a Senior Java developer transitioning into AI Engineering.

> Built as the **AI Service layer** for a larger microservices Insurance Platform (coming in Phase 2).

---

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Java 17 | Core language |
| Spring Boot 4.0.x | Application framework |
| Spring AI 2.0.0 | AI integration framework |
| Groq API (`llama-3.3-70b-versatile`) | LLM provider for chat |
| Ollama (`nomic-embed-text`) | Local embeddings for RAG |
| Maven | Build tool |

---

## ✨ What's Built — Week by Week

### ✅ Week 1 — Basic AI Endpoints
First working AI-powered Spring Boot APIs. The foundation everything else builds on.

- Simple Q&A endpoint
- Code review endpoint
- Text summarization
- Custom role-based responses (system prompt control)

**Key learning:** Calling an LLM from Spring Boot is just a service call. `ChatClient` abstracts everything.

---

### ✅ Week 2 — Structured Output + Streaming

**Part A — Structured Output**
Making AI return typed Java objects instead of plain text strings.

- `DeveloperProfile` extraction from free-text descriptions
- `MovieRecommendation` as a typed record
- `CodeReviewResult` with score, bugs list, and improvement suggestions
- List of typed objects using `ParameterizedTypeReference`

**Part B — Streaming**
Real-time word-by-word responses using Server-Sent Events (SSE).

- Basic SSE streaming endpoint
- Streaming with custom system prompts
- HTML demo page to visualize streaming in browser

**Key learning:** `.call()` for blocking responses, `.stream()` for real-time. `.entity(MyClass.class)` maps AI output directly to Java records.

---

### ✅ Week 3 — Tools (Function Calling)
Giving AI access to real Java methods — AI decides when and how to call them.

- `WeatherTools` — current weather and 3-day forecast
- `MathTools` — typed arithmetic and compound interest calculator
- `DateTimeTools` — current date/time, days between dates, day-of-week lookup
- `DatabaseTools` — mock product catalog and order status lookup
- Multi-tool orchestration — AI calls multiple tools in a single request

**Key learning:** `@Tool` description quality directly determines whether AI calls the method correctly. Typed parameters are far more reliable than `String expression` inputs.

---

### ✅ Week 4 — Conversation Memory
Session-based memory so AI remembers what you told it earlier in the conversation.

- `MessageWindowChatMemory` — keeps last 20 messages per session
- `sessionId` isolates each user's conversation independently
- `/new-session` endpoint generates a fresh conversation ID
- Session isolation verified — different sessions have zero shared context

**Key learning:** In Spring AI 2.0.0, the correct memory param key is `"chat_memory_conversation_id"`. Source code (`BaseChatMemoryAdvisor`) is the most reliable reference when docs lag behind milestone releases.

---

### ✅ Week 5 — RAG (Retrieval Augmented Generation)
AI that answers questions strictly from your own uploaded documents — no hallucination.

- PDF upload and chunking via `PagePdfDocumentReader` + `TokenTextSplitter`
- In-memory vector store (`SimpleVectorStore`) — no Docker or PostgreSQL required
- Local embeddings via Ollama (`nomic-embed-text`) — free and offline
- `QuestionAnswerAdvisor` wires vector search into every prompt automatically
- Verified "I don't know" responses for questions not covered by uploaded documents

**Key learning:** Groq doesn't support embeddings — mixed-provider setup (Groq for chat + Ollama for embeddings) is a real-world pattern. When two auto-configured beans of the same type conflict, excluding the unwanted `@AutoConfiguration` class at the `@SpringBootApplication` level is cleaner than `@Primary` or `@Qualifier` alone.

---

### ✅ Week 6 — Production Hardening
Cleaning up the entire service for real-world use.

- `ApiResponse<T>` — consistent response wrapper across all endpoints
- `GlobalExceptionHandler` — centralized error handling with proper HTTP status codes
- `AiServiceException` — custom exception for AI-layer failures
- Input validation on all service methods
- Clean package structure: `controller → service → config → tools → exception → dto`

---

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- [Groq API Key](https://console.groq.com) (free tier available)
- [Ollama](https://ollama.com) installed locally

### Setup

**1. Clone the repo**
```bash
git clone https://github.com/nvsmadhu-lab/spring-ai-starter.git
cd spring-ai-starter
```

**2. Pull the embeddings model**
```bash
ollama pull nomic-embed-text
```

**3. Configure `application.properties`**
```properties
# Groq — chat model
spring.ai.openai.api-key=your_groq_api_key_here
spring.ai.openai.base-url=https://api.groq.com/openai/v1
spring.ai.openai.chat.options.model=llama-3.3-70b-versatile
spring.ai.openai.embedding.enabled=false

# Ollama — embeddings only
spring.ai.ollama.base-url=http://localhost:11434
spring.ai.ollama.embedding.options.model=nomic-embed-text
spring.ai.ollama.chat.enabled=false

server.port=8080
spring.application.name=ai-starter
```

**4. Run**
```bash
mvn spring-boot:run
```

---

## 📡 API Reference

### Basic AI — `/api/ai`
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/ask?question=` | Simple Q&A |
| POST | `/ask-with-role` | Q&A with custom system role |
| POST | `/review-code` | Plain-text code review |
| POST | `/summarize` | Text summarization |

### Structured Output — `/api/structured`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/developer-profile` | Extract typed `DeveloperProfile` from text |
| POST | `/recommend-movie` | Single typed `MovieRecommendation` |
| POST | `/review-code` | Scored `CodeReviewResult` with bug list |
| POST | `/recommend-movies` | List of typed recommendations |

### Streaming — `/api/stream`
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/ask?question=` | SSE streaming response |
| POST | `/ask-with-role` | Streaming with custom system role |
| POST | `/explain-code` | Streaming code explanation |

### Tools — `/api/tools`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/chat` | AI with access to all tools |
| POST | `/weather` | AI scoped to weather tools only |

### Memory — `/api/memory`
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/new-session` | Generate a new `sessionId` |
| POST | `/chat` | Chat with persistent session memory |

### RAG — `/api/rag`
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/upload` | Upload and index a PDF |
| POST | `/ask` | Ask questions grounded in uploaded documents |

---

## 💡 Engineering Decisions Worth Noting

**Provider abstraction**
Spring AI decouples your code from LLM providers completely. Swapping Groq → OpenAI → Anthropic requires changing 3 lines in `application.properties`. Zero Java code changes.

**Mixed-provider architecture**
Chat runs on Groq (fast, generous free tier). Embeddings run on Ollama locally (Groq doesn't support embeddings). This is a realistic pattern used in production for cost and latency optimization.

**`SimpleVectorStore` for development**
Removes the Docker/PostgreSQL dependency while learning RAG concepts. Swappable for `PgVectorStore` in production with a single bean change — zero service or controller changes needed.

**`@SpringBootApplication(exclude = ...)` over `@Primary`**
When Spring AI auto-configures two `ChatModel` beans (OpenAI + Ollama), `@Primary` doesn't help because the conflict is inside Spring AI's own `ChatClientAutoConfiguration`. Excluding `OllamaChatAutoConfiguration` entirely at the application level removes the conflict at its source.

**Java Records for DTOs**
All request/response models use Java records instead of `@Data` classes — immutable by default, less boilerplate, and Jackson deserializes them cleanly with Spring Boot 4.x.

---

## 🗺️ Roadmap

### Phase 1 — AI Service ✅ Complete
- [x] Week 1 — Basic AI endpoints
- [x] Week 2 — Structured Output + Streaming
- [x] Week 3 — Tools / Function Calling
- [x] Week 4 — Conversation Memory
- [x] Week 5 — RAG over custom documents
- [x] Week 6 — Production hardening

### Phase 2 — Insurance Platform (Coming Next)
- [ ] Policy Service — Spring Boot + REST + PostgreSQL + Hibernate
- [ ] Party Service — Spring Boot + GraphQL
- [ ] Service-to-service communication — gRPC
- [ ] API Gateway — Spring Cloud Gateway
- [ ] Authentication — Keycloak (OAuth2 + JWT)
- [ ] Event streaming — Apache Kafka
- [ ] AI Service integration — connect this project as the AI layer
- [ ] Containerization — Docker Compose for local dev
- [ ] Orchestration — Kubernetes

---

## 👤 About

Built by **Madhu** — Senior Java Engineer with 6.8 years of experience in Spring Boot, Microservices, Kafka, and the insurance domain, currently transitioning into AI Engineering.

- 🔗 [LinkedIn](https://www.linkedin.com/in/nvsmadhumitra/)
- 💻 [GitHub](https://github.com/nvsmadhu-lab)
