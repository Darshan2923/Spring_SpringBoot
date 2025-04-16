# 🏥 CuraSync: Comprehensive Hospital Management Microservices Platform

CuraSync is a powerful, scalable hospital management system built using **Spring Boot microservices architecture**. It integrates patient data handling, billing, and real-time analytics through modular services, enabling efficient and secure healthcare operations.

---

## 🚀 Project Overview

CuraSync is designed to tackle the challenges of modern hospital operations with a microservices-first approach. Here's what powers the platform:

- **🔐 Role-Based Authentication & Authorization**  
  Fine-grained user access control for staff and administrators.

- **🧑‍⚕️ Patient Service**  
  Central service for managing patient data and records.

- **💳 Billing Service (gRPC)**  
  Handles real-time billing events using high-speed gRPC communication.

- **📊 Analytics & Notification Service (Kafka)**  
  Asynchronous Kafka streams track and respond to patient activity.

- **🚪 API Gateway**  
  Manages routing, load balancing, and request validation for all services.

- **🧪 Integration Testing**  
  Ensures seamless service-to-service communication using gRPC and Kafka.

> The system runs locally using Docker and simulates AWS deployment with LocalStack.

---

## 🌟 Features Highlight

- ⚡ **Real-time Billing and Notification**  
  Built with gRPC and Kafka for high-speed service communication.

- 📈 **Scalability & Resilience**  
  Kafka-backed async architecture enhances performance.

- 🔐 **API Security and Optimization**  
  API Gateway routes and protects traffic across microservices.

- ☁️ **Cloud Compatibility**  
  Easily deployable to AWS — simulated with LocalStack for local testing.

- 🧩 **Service Modularity**  
  Microservices can be scaled and maintained independently.

---

## 🛠️ Tech Stack

- **Spring Boot** – Java framework for building microservices  
- **Apache Kafka** – Messaging layer for event-driven architecture  
- **gRPC** – High-performance inter-service communication  
- **Docker** – Containerization for easy local and production setup  
- **AWS (simulated via LocalStack)** – Cloud compatibility and testing  
- **PostgreSQL** – Relational data storage for each service  

---

## 🏗️ Architecture Diagrams

### 🔧 Development Architecture  
_(Insert professional system-level diagram)_

### ☁️ Deployment Architecture  
_(Insert clean diagram simulating AWS cloud services and routing)_

---

## 💻 Local Setup Guide

### ✅ Requirements

- Java JDK  
- Docker  
- AWS CLI  
- LocalStack  
- VS Code / IntelliJ IDEA

### 🛠 Steps to Set Up

1. **Clone the Repository:**

   ```bash
   git clone <your-github-repo-link>
   ```
2.**Build Docker Images for Each Microservice:**
    ```bash
    cd <service-name>
    docker build -t <service-name> -f Dockerfile .

    ```
    Repeat this step for each service you want to run.

3.**Launch Infrastructure via LocalStack:**
    ```bash
    cd infrastructure
    ./localstack-deploy.sh

    ```

4.**Access API Documentation via Swagger:**
    ```bash
    http://<localhost-address>:4004/api-docs/auth
    
    ```

### Authentication Example (Patient Service)

**Example Login Endpoint:**
```bash
    POST http://lb-1e2646ea.elb.localhost.localstack.cloud:4004/auth/login

```
**Example Request Body**
```bash
    {
        "email": "testuser@test.com",
        "password": "password123"
    }

```

**Authorization Header:**
```bash
    Authorization: Bearer <token_from_login_response>
```
Use this token in the Authorization header for protected routes.

## ⚠️ Challenges Faced

- Integration complexity with gRPC and Kafka

- Kafka memory management and handling large message loads

- Simulating cloud services with LocalStack effectively

## 📚 What I Learned

- Real-time microservices communication using gRPC and Kafka

- Kafka streams for scalable, asynchronous analytics

- Simulating AWS locally with LocalStack

- Structuring maintainable, testable microservices

## 🚧 Future Scope

- Implement advanced checkout and billing workflows

- Develop a sleek frontend UI for hospital staff

- Add robust logging, monitoring, and alerting tools

- Deploy to actual AWS infrastructure using ECS or EKS

## 🎥 Project Demo Video

-- To be added soon...



