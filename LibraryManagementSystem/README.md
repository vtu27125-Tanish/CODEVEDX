# Library Management System

A console-based Library Management System built in Java, using JDBC to connect to a MySQL database. Built as part of the CodeVedX Java Development Internship (Level 2 - Task 3).

## Features

- **Add Book** — insert a new book record (title, author)
- **View Available Books** — display only books that are currently not issued
- **Issue Book** — assign a book to a student by ID
- **Return Book** — mark a book as available again
- **View All Books** — display every book with its current status (available / issued)

## Tech Stack

- Java
- JDBC
- MySQL

## Project Structure

```
LibraryManagementSystem/
├── LibraryMain.java          # Console menu and program entry point
├── Book.java                  # Book model class (POJO)
├── BookDAO.java                # Database access logic (CRUD + issue/return)
├── LibraryDBConnection.java     # Handles the MySQL connection
├── library.sql                  # SQL schema for the books table
└── README.md
```

## Database Setup

Run the following in MySQL before starting the app:

```sql
CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

CREATE TABLE IF NOT EXISTS books (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(150) NOT NULL,
    author VARCHAR(100) NOT NULL,
    is_available BOOLEAN NOT NULL DEFAULT TRUE,
    issued_to VARCHAR(100)
);
```

## How to Run

1. Set up the database using `library.sql`
2. Update the MySQL username/password in `LibraryDBConnection.java` if needed
3. Compile and run `LibraryMain.java`
4. Use the on-screen menu to add, view, issue, or return books

## Sample Menu

```
===== LIBRARY MANAGEMENT SYSTEM =====
1. Add Book
2. View Available Books
3. Issue Book
4. Return Book
5. View All Books
6. Exit
```

## About

Built as part of the **CodeVedX** Java Development Internship.
