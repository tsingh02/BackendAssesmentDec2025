# 🛒 E-Commerce Application

## Table of Contents
- [Overview](#overview)
- [Features](#features)
- [Technology Stack](#technology-stack)
- [Setup Instructions](#setup-instructions)
- [Database Migrations](#database-migrations)
- [Configuration](#configuration)
- [API Endpoints](#api-endpoints)
    - [Authentication](#authentication)
    - [Products](#products)
    - [Orders](#orders)
- [Discount Rules](#discount-rules)
- [Pagination & Sorting](#pagination--sorting)
- [Error Handling](#error-handling)
- [Testing](#testing)
- [Swagger Documentation](#swagger-documentation)
- [Docker Setup](#docker-setup)
- [Logging & Actuator](#logging--actuator)

---

## Overview
This is a **Spring Boot 3** e-commerce application for managing **products, users, and orders**, featuring:

- JWT-based authentication
- Role-based access control
- Dynamic discount computation based on user type and order amount
- Pagination, filtering, caching support
- Production-ready exception handling and logging

---

## Features

### Product Management
- CRUD with soft-delete 
- Search/filter by name, price range, and availability
- Timestamps (`createdAt`, `updatedAt`)

### User Management
- Roles: `USER`, `PREMIUM_USER`, `ADMIN`
- JWT authentication
- Role-based access control

### Order Management
- Place orders with multiple products (Only allowed for users with Roles:`USER`, `PREMIUM_USER` )
- Validate stock; reject if insufficient
- Apply dynamic discounts
- Update inventory on successful order

### Bonus Features
- Global exception handling via `@ControllerAdvice`
- Logging with SLF4J
- Spring Boot Actuator endpoints

---

## Technology Stack
- Java 17+
- Spring Boot 3.2+
- Spring Web, Spring Data JPA, Spring Security
- H2 
- Liquibase for database migrations
- JWT Authentication
- Springdoc OpenAPI (Swagger UI)
- Lombok
- JUnit 5 & Mockito for testing

---

## Setup Instructions

### 1. Clone Repository
```bash
git clone https://github.com/tsingh/ecommerce-app.git
cd ecommerce-app

### 2. Build Project
mvn clean install -Dskiptests
run as springboot applicaiton

### 3. download the postman collection from src/main/resources/postmanCollection direcotry
### 4. Create User via /auth/createUser endpoint
### 5. Authenticate User via /auth/login endpoint to get JWT token
### 6. Use JWT token as bearer token to access secured endpoints
