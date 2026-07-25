# AI Resume Analyzer - System Flow

# User Flow

Landing Page
│
▼
Register / Login
│
▼
Dashboard
│
├──────────────┐
▼              ▼
Upload Resume     Upload/Paste Job Description
│              │
└──────┬───────┘
▼
Click Analyze
│
▼
API Gateway
│
▼
Analysis Service
│
┌─────────┴─────────┐
▼                   ▼
Resume Service      AI Service
│                   │
└─────────┬─────────┘
▼
Generate Analysis
│
▼
Save to PostgreSQL
│
▼
Notification Service (Kafka)
│
▼
Dashboard Result
│
┌─────────┼──────────────┐
▼         ▼              ▼
ATS Score  Suggestions   Skill Gap
│
▼
Download PDF Report
│
▼
Analysis History
│
▼
AI Features
│
├── Resume Rewrite
├── Cover Letter
├── Mock Interview
├── Career Suggestions
└── Job Match Finder

------------------------------------------------

Backend Communication

Angular
│
▼
API Gateway
│
▼
Auth Service
│
▼
Resume Service
│
▼
Analysis Service
│
▼
AI Service
│
▼
Notification Service
│
▼
PostgreSQL / Redis / Kafka

------------------------------------------------

Deployment

Angular
│
▼
Nginx
│
▼
AWS EC2

↓

Spring Boot Microservices

↓

AWS RDS
AWS S3
Redis
Kafka

↓

CloudWatch
Prometheus
Grafana