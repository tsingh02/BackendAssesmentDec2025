--liquibase formatted sql

INSERT INTO users (email, password, role)
VALUES (
    'admin@test.com',
    '$2a$10$ZJZ7m6nPZ6O7ZQZ6pTnE0uJ3g9B5P5A9p6wB3F5yYQZr0uZ5yJmB2',
    'ADMIN'
);