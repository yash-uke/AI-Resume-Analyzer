# AI Resume Analyzer - API Documentation

## Base URL

/api/v1

---

# Authentication APIs

POST /auth/register
POST /auth/login
POST /auth/google
POST /auth/refresh-token
POST /auth/logout
POST /auth/forgot-password
POST /auth/reset-password
GET /auth/verify-email

---

# User APIs

GET /users/profile
PUT /users/profile
PUT /users/change-password
DELETE /users/account

---

# Resume APIs

POST /resumes/upload
GET /resumes
GET /resumes/{id}
DELETE /resumes/{id}

---

# Job Description APIs

POST /jobs/upload
POST /jobs/paste
GET /jobs
GET /jobs/{id}
DELETE /jobs/{id}

---

# Analysis APIs

POST /analysis/start
GET /analysis/{id}
GET /analysis/history
DELETE /analysis/{id}

---

# AI APIs

POST /ai/rewrite-resume
POST /ai/cover-letter
POST /ai/mock-interview
POST /ai/career-suggestions

---

# Report APIs

GET /reports/{id}/download

---

# Dashboard APIs

GET /dashboard

---

# Notification APIs

GET /notifications
PUT /notifications/read/{id}

---

# Health APIs

GET /actuator/health
GET /actuator/info