cat > README.md << 'EOF'
# TripPlanner Spring Boot Backend

This is the backend for the TripPlanner project. It manages user authentication, trip creation/joining, trip dashboard, profile management, and integrates with LangChain4j for AI-powered itinerary generation.

---

## Table of Contents

- [Features](#features)
- [Technology Stack](#technology-stack)
- [Project Structure](#project-structure)
- [Setup and Installation](#setup-and-installation)
- [Application Properties](#application-properties)
- [Dependencies](#dependencies)
- [Database](#database)
- [Running the Application](#running-the-application)
- [API Endpoints](#api-endpoints)
- [LangChain4j & OpenAI Integration](#langchain4j--openai-integration)
- [Troubleshooting](#troubleshooting)

---

## Features

- OAuth2 authentication (Google/GitHub)
- User profile management (view and edit)
- Trip CRUD: create, join, update, approve/deny
- Dashboard API for frontend
- Itinerary generation using AI (LangChain4j + OpenAI)
- RESTful endpoints
- SQL database persistence

---

## Technology Stack

- Java 17+
- Spring Boot
- Spring Data JPA (Hibernate)
- Spring Security (OAuth2)
- LangChain4j
- OpenAI API
- PostgreSQL/MySQL/H2 (adjust as needed)
- Maven

---

- **controller** - REST endpoints
- **model** - entity classes (Trip, User)
- **repository** - JPA repositories
- **dto** - data transfer objects
- **service** - business & AI logic
- **config** - bean and security configuration

---

## Setup and Installation

1. Clone the repository:


2. Make sure you have JDK 17+, Maven, and a SQL database installed.

3. Configure application properties (see below).

4. Build the project:


---

## Application Properties

Edit `src/main/resources/application.properties`:

DB sample (PostgreSQL; adjust for MySQL/H2)
spring.datasource.url=jdbc:postgresql://localhost:5432/tripdb
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

OAuth2: Google, GitHub credentials
spring.security.oauth2.client.registration.google.client-id=your-google-client-id
spring.security.oauth2.client.registration.google.client-secret=your-google-client-secret
spring.security.oauth2.client.registration.github.client-id=your-github-client-id
spring.security.oauth2.client.registration.github.client-secret=your-github-client-secret

OpenAI LangChain4j
openai.api-key=YOUR_OPENAI_API_KEY


---

## Dependencies

In your `pom.xml`, include:

<dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-data-jpa</artifactId> </dependency> <dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-web</artifactId> </dependency> <dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-security</artifactId> </dependency> <dependency> <groupId>org.springframework.boot</groupId> <artifactId>spring-boot-starter-oauth2-client</artifactId> </dependency> <dependency> <groupId>org.postgresql</groupId> <artifactId>postgresql</artifactId> <scope>runtime</scope> </dependency> <dependency> <groupId>dev.langchain4j</groupId> <artifactId>langchain4j-open-ai</artifactId> <version>0.35.0</version> </dependency> ```

# API Endpoints (key examples)
POST /api/trips/create - Create a new trip

GET /api/trips/my - Get trips of logged-in user

GET /api/trips/all - List all trips

GET /api/trips/{id} - Get trip details

POST /api/trips/{id}/join - Join a trip

POST /api/trips/{id}/generate-itinerary - Generate AI itinerary

PUT /api/trips/{id}/update - Update trip

POST /api/trips/{id}/status?action=approve - Approve/deny a trip

GET /api/user - Get basic user info

GET /api/user/profile - Get full user profile

PUT /api/user/profile - Update user profile
