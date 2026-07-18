-- Library Management System - Database Schema

CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    issued_to VARCHAR(100)
);

-- Sample data
INSERT INTO books (title, author) VALUES
('The Alchemist', 'Paulo Coelho'),
('Clean Code', 'Robert C. Martin');