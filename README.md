# 🛒 E-Commerce Application

## 📌 Overview
This is a **Spring Boot 3** based e-commerce application designed to manage **products, users, and orders** with a strong focus on **security, clean architecture, and scalability**.

The application features:
- JWT-based authentication
- Role-based access control
- Dynamic discount computation
- Production-ready exception handling and logging
- Testing with JUnit 5 and Mockito

---

## 🚀 Features

### 📦 Product Management
- Full CRUD operations with **soft delete**
- Search and filter by:
    - Name
    - Price range
    - Availability
- Automatic timestamps:
    - `createdAt`
    - `updatedAt`

---

### 👤 User Management
- Supported roles:
    - `USER`
    - `PREMIUM_USER`
    - `ADMIN`
- Secure **JWT authentication**
- Role-based authorization:
    - `ADMIN`: Full product management
    - `USER` / `PREMIUM_USER`: View products & place orders

---

### 🛒 Order Management
- Place orders containing **multiple products**
- Order validation:
    - Rejects orders with insufficient stock
- Automatic inventory updates after successful orders
- **Dynamic discount computation**:
    - `USER`: No discount
    - `PREMIUM_USER`: 10% discount
    - Orders above `$500`: Additional 5% discount
- Discounts implemented using a **Strategy Design Pattern**

---

### ⭐ Bonus Features
- Global exception handling using `@ControllerAdvice`
- Centralized logging with **SLF4J**
- Spring Boot **Actuator** endpoints
- Pagination and filtering support
- OpenAPI / Swagger documentation

---

## 🧰 Technology Stack

| Category | Technology |
|--------|-----------|
| Language | Java 17+ |
| Framework | Spring Boot 3.2+ |
| Web | Spring Web |
| Persistence | Spring Data JPA |
| Security | Spring Security + JWT |
| Database | H2 |
| Migrations | Liquibase |
| API Docs | Springdoc OpenAPI (Swagger UI) |
| Utilities | Lombok |
| Testing | JUnit 5, Mockito |

---

## 📚 API Endpoints

### 🔐 Authentication (`auth-controller`)
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/auth/login` | Authenticate user and get JWT |
| POST | `/auth/createUser` | Register a new user |
| GET | `/auth/getUsers` | Get all users (ADMIN only) |

---

### 📦 Products (`product-controller`)
| Method | Endpoint | Description |
|------|---------|------------|
| GET | `/api/products/{id}` | Get product by ID |
| GET | `/api/products` | Search & filter products |
| POST | `/api/products/create` | Create a product |
| PUT | `/api/products/{id}` | Update product |
| DELETE | `/api/products/{id}` | Delete product |

---

### 🛒 Orders (`order-controller`)
| Method | Endpoint | Description |
|------|---------|------------|
| POST | `/api/orders/create` | Place an order |
| GET | `/api/orders` | Get current user orders |
| GET | `/api/orders/{id}` | Get order by ID |

---

## 🛠 Setup Instructions


### 1️⃣ Clone the Repository
```bash
git clone https://github.com/tsingh02/BackendAssesmentDec2025
cd ecommerce-app

2️⃣ Build the Project
mvn clean install -DskipTests

3️⃣ Run the Application
Run as a Spring Boot Application from your IDE
or using Maven:
mvn spring-boot:run

4️⃣ Swagger UI
Access API documentation at:
http://localhost:8080/swagger-ui/index.html

5️⃣ Postman Collection
A ready-to-use Postman collection is available at:
src/main/resources/postmanCollection

6️⃣ Authentication Flow
Create a user using /auth/createUser
Authenticate using /auth/login
Copy the JWT token
Use the token as a Bearer Token for secured endpoints
