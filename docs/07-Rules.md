# AI Resume Analyzer - Development Rules

# General Rules

- Keep code clean and readable.
- Follow SOLID principles.
- Follow DRY (Don't Repeat Yourself).
- Follow KISS (Keep It Simple, Stupid).
- Avoid unnecessary complexity.

---

# Naming Conventions

Packages
- lowercase

Classes
- PascalCase

Methods
- camelCase

Variables
- camelCase

Constants
- UPPER_CASE

---

# Backend Standards

- Use Constructor Injection
- Use DTOs for API communication
- Never expose entities directly
- Use Global Exception Handler
- Validate all requests
- Use ResponseEntity
- Use Lombok where appropriate

---

# API Standards

- RESTful APIs
- Version APIs (/api/v1)
- Proper HTTP Status Codes
- Consistent JSON response format

---

# Database Rules

- UUID for IDs
- Use Flyway for migrations
- Avoid duplicate data
- Add indexes where required

---

# Security Rules

- JWT Authentication
- BCrypt Password Encoding
- Role-Based Access Control (RBAC)
- Validate JWT at Gateway

---

# Logging

- Use SLF4J
- Log errors with context
- Avoid logging passwords or tokens

---

# Testing

- Unit Tests
- Integration Tests
- API Testing with Postman

---

# Git Rules

Branch Naming

feature/<feature-name>

bugfix/<bug-name>

hotfix/<bug-name>

Commit Messages

feat:
fix:
refactor:
docs:
test:

---

# AI Assistant Rules

The AI assistant must NEVER:

- Delete project files without approval
- Change database schema without approval
- Remove APIs without approval
- Add dependencies without approval

If unsure,

ASK FIRST.