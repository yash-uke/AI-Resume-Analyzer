# AI Resume Analyzer - Architecture

# Architecture Style

Microservices Architecture

---

# Frontend

- Angular
- Angular Material
- TypeScript

---

# API Gateway

Responsibilities:
- Route Requests
- Authentication
- Rate Limiting
- Logging
- CORS

Technology:
- Spring Cloud Gateway

---

# Discovery Server

Responsibilities:
- Service Discovery

Technology:
- Eureka Server

---

# Backend Services

## Auth Service

Responsibilities
- User Registration
- Login
- Google OAuth
- JWT
- Refresh Token

Database
- PostgreSQL

---

## Resume Service

Responsibilities

- Upload Resume
- Store Resume
- Extract PDF
- Extract DOCX
- Resume History

Database

- PostgreSQL

---

## Analysis Service

Responsibilities

- ATS Score
- Resume Analysis
- Keyword Analysis
- Skill Gap

Database

- PostgreSQL

---

## AI Service

Responsibilities

- OpenAI
- Gemini
- Claude
- Ollama
- Prompt Management

---

## Notification Service

Responsibilities

- Email
- Kafka Consumer
- Alerts

---

# Database

- PostgreSQL

---

# Cache

- Redis

---

# Messaging

- Apache Kafka

---

# Object Storage

- AWS S3

---

# Containerization

- Docker

---

# Monitoring

- Prometheus
- Grafana

---

# Logging

- ELK Stack

---

# CI/CD

- GitHub Actions

---

# Deployment

AWS

- EC2
- RDS
- S3
- IAM
- CloudWatch

---

# Service Communication

Frontend

↓

API Gateway

↓

Auth Service

↓

Resume Service

↓

Analysis Service

↓

AI Service

↓

Notification Service

---

# Authentication Flow

User

↓

Login

↓

JWT Generated

↓

JWT Sent to Frontend

↓

JWT Added in API Calls

↓

Gateway Validation

↓

Microservice Access