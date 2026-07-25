# AI Resume Analyzer - Database Schema

# Database

PostgreSQL

---

## Users

Fields

- id (UUID)
- first_name
- last_name
- email
- password
- profile_image
- provider (LOCAL, GOOGLE)
- role
- is_verified
- created_at
- updated_at

---

## Resume

Fields

- id
- user_id
- resume_name
- file_name
- file_type
- file_size
- s3_url
- extracted_text
- uploaded_at

Relationship

User (1) ------ (M) Resume

---

## Job Description

Fields

- id
- user_id
- title
- company
- description
- uploaded_at

Relationship

User (1) ------ (M) Job Description

---

## Resume Analysis

Fields

- id
- resume_id
- job_description_id
- ats_score
- keyword_score
- grammar_score
- technical_score
- soft_skill_score
- overall_score
- ai_summary
- created_at

Relationship

Resume (1) ------ (M) Resume Analysis

---

## Missing Keywords

Fields

- id
- analysis_id
- keyword

Relationship

Analysis (1) ------ (M) Missing Keywords

---

## Matched Skills

Fields

- id
- analysis_id
- skill

---

## Suggestions

Fields

- id
- analysis_id
- title
- description
- priority

---

## Cover Letter

Fields

- id
- user_id
- resume_id
- job_description_id
- content
- created_at

---

## Mock Interview

Fields

- id
- user_id
- job_description_id
- questions
- difficulty
- created_at

---

## AI Request Logs

Fields

- id
- provider
- prompt_tokens
- completion_tokens
- total_tokens
- response_time
- status
- created_at