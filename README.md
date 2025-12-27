# ☕ Coffee API

A Spring Boot REST API for managing coffee items and orders.
This project is built to practice backend development, API design, and clean project structure.

---

## 🚀 Tech Stack
- Java
- Spring Boot
- REST APIs
- Maven
- MySQL (configurable)
- Git & GitHub

---

## 📂 Project Structure

src/main/java/com/coffeeshop/coffee_api
├── controller
│ ├── CoffeeController
│ ├── OrderController
│ └── HealthController
├── model
│ ├── Coffee
│ ├── Order
│ └── OrderStatus
└── CoffeeApiApplication


----

## 🔗 API Endpoints
http://localhost:8080

### 🩺 Health Check
http://localhost:8080/health
Coffee API is running ☕

### 🧾 Order APIs
GET /coffees
POST /coffees

## ▶️ How to Run
```bash
./mvnw spring-boot:run


Application runs on:

http://localhost:8080




📌 Purpose

This project was built to:

Spring Boot REST APIs

Practice controller–model structure

Understand real backend workflows
