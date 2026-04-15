# 🚀 Employee Management System – Backend

## 📌 Overview

This is the backend service for the Employee Management System built using **Spring Boot**. It provides secure REST APIs for managing employees and departments with authentication and AI-powered features.

---

## 🛠 Tech Stack

* Java, Spring Boot
* Spring Security (JWT Authentication)
* Spring Data JPA (Hibernate)
* PostgreSQL / MySQL
* Docker (Multi-stage build)
* GitHub Actions (CI/CD)

---

## 🔐 Features

* JWT-based Authentication & Authorization (RBAC)
* Secure Login & Registration
* Employee & Department CRUD APIs
* Search and Pagination for efficient data handling
* CORS Configuration for frontend integration
* AI Chat Integration using Google Gemini API
* Dockerized application for easy deployment

---

## 🤖 AI Chat Feature

* Integrated **Gemini API (gemini-1.5-flash)**
* Provides dynamic responses about project features
* Handles structured JSON responses

---

## 📦 API Endpoints (Sample)

| Method | Endpoint        | Description                              |
| ------ | --------------- | ---------------------------------------- |
| POST   | /auth/login     | User login                               |
| POST   | /auth/register  | User registration                        |
| GET    | /employees      | Get all employees (pagination supported) |
| POST   | /employees      | Add employee                             |
| PUT    | /employees/{id} | Update employee                          |
| DELETE | /employees/{id} | Delete employee                          |

---

## 🐳 Docker Setup

```bash
docker build -t employee-backend .
docker run -p 8080:8080 employee-backend
```

---

## ⚙️ Run Locally

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🔗 Frontend Repo

https://github.com/helllo24/Login_FrontendProject.git

---

## 💡 Highlights

* Production-ready backend with security
* Optimized using pagination & search
* Integrated AI for modern functionality
