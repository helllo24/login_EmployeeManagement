# Employee Management System - Backend API

A robust REST API built with Spring Boot, providing secure data management and AI-powered capabilities.

## 🚀 Live API
**Base URL:** [https://login-employeemanagement-3.onrender.com](https://login-employeemanagement-3.onrender.com)

## ✨ Features & Functionality
* **JWT Security:** Implements `JwtAuthenticationFilter` and `SecurityConfig` to protect endpoints. Only users with `ROLE_ADMIN` can modify employee data.
* **Full CRUD API:**
    * `GET /employee/findAll`: Fetches all employees with nested department details.
    * `POST /employee/add`: Validates and saves new employee records.
    * `PUT /employee/update/{empid}`: Updates records using an `EmpDto` to map department IDs.
    * `DELETE /employee/delete/{empid}`: Securely removes employee entities.
* **Automated Email Service:** Uses `JavaMailSender` to send automated OTPs and verification links for account security.
* **Generative AI Integration:** Custom endpoints utilizing **Google Gemini 1.5 Flash** for intelligent data processing or assistance.
* **Relational Database:** Managed via Spring Data JPA with PostgreSQL.

## 🛠️ Tech Stack
* **Java 17+** & **Spring Boot 3.x**
* **Spring Security:** Stateless JWT-based authentication.
* **Lombok:** For clean, boilerplate-free DTOs and Entities.
* **Maven:** Dependency management.

## 🔑 Key Configurations
* **CORS Filter:** Configured to allow `GET`, `POST`, `PUT`, `DELETE`, and `OPTIONS` from the Vercel frontend.
* **DTO Mapping:** Uses `EmpDto` to safely transfer data between the client and the database, specifically handling the relationship between Employees and Department IDs.

## ⚙️ How to Run
1. Configure `application.properties` with your Database and Gmail SMTP credentials.
2. Add your `gemini.api.key`.
3. Run `mvn spring-boot:run`.
