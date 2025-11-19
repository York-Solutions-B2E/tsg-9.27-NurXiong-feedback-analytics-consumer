# Feedback Analytics Consumer

**Repo:** `tsg-9.27-NurXiong-feedback-analytics-consumer`

## Overview

This is **Spring Boot App 2** of the Provider Feedback Portal ecosystem. It acts as a standalone Kafka Consumer service designed to simulate an analytics ingestion pipeline.

**Responsibilities:**
    
1.  Connects to the Kafka cluster.
2.  Subscribes to the `feedback-submitted` topic.
3.  Deserializes incoming JSON events.
4.  Simulates analytics processing via structured logging.

## Tech Stack

* **Language:** Java 21
* **Framework:** Spring Boot 3.x
* **Messaging:** Apache Kafka (Spring Kafka)
* **Build Tool:** Maven
* **Containerization:** Docker

## Project Structure

```text
src/main/java/com/tsg/feedbackconsumer/
├── controllers/
│   └── HealthController.java      # Exposes GET /health
├── messaging/
│   ├── KafkaConsumerConfig.java   # Deserialization & Consumer factory config
│   └── FeedbackEventListener.java # @KafkaListener logic
└── FeedbackConsumerApplication.java
```

## Kafka Contract
This service consumes messages from the feedback-submitted topic.

Event Schema (JSON):

```json
JSON
{
  "id": "5f1a4d9e-89b1-42a3-9070-6525075950b2",
  "memberId": "m-123",
  "providerName": "Dr. Smith",
  "rating": 4,
  "comment": "Great experience.",
  "submittedAt": "2025-11-10T20:23:00Z",
  "schemaVersion": 1
}
```

## Running the Application
### 1. Docker Compose

This service is part of a larger ecosystem. The docker-compose.yaml file required to run the full stack (Database, Kafka, API, Consumer, and UI) is located in the feedback-api repository.

Clone the other repositories
--
Feedback-api: https://github.com/York-Solutions-B2E/tsg-9.27-NurXiong-feedback-api.git

Feedback-ui: https://github.com/York-Solutions-B2E/tsg-9.27-nurxiong-frontend-feedback-ui.git

```bash
git clone https://github.com/York-Solutions-B2E/tsg-9.27-NurXiong-feedback-api.git

git clone https://github.com/York-Solutions-B2E/tsg-9.27-nurxiong-frontend-feedback-ui.git
```

To run the full system, All repo's must be in a common parent folder! Navigate to the feedback-api directory and run:

```bash
docker compose up -d --build
```
### 2. Local Testing (Java 21 required)

```bash
mvn clean compile

mvn test
```

### 3. Dockerized tests

```bash
./test.sh
```
