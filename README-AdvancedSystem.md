# Advanced Student & Library Management System

A REST API built with Java, Spring Boot, and MySQL, featuring role-based access control (Admin/User), student management, book management, and book issue/return tracking with full history. Built as part of the CodeVedX Java Development Internship (Level 3).

## Features

- **Student Management** — enroll, view, and delete student records
- **Book Management** — add books, view all books, view only available books
- **Issue / Return System** — issue a book to a student, return a book, with full timestamped history
- **Role-Based Access** — ADMIN can add/delete data, USER can only view (read-only)

## Tech Stack

- Java 17
- Spring Boot 3
- Spring Data JPA (Hibernate)
- Spring Security (role-based auth)
- MySQL
- Maven

## Project Structure

```
src/main/java/com/codevedx/library/
├── LibraryApplication.java      # Main entry point
├── config/SecurityConfig.java   # Role-based access setup (Admin/User)
├── model/                       # Student, Book, IssueRecord entities
├── repository/                  # Spring Data JPA repositories
├── service/                     # Business logic
└── controller/                  # REST API endpoints
src/main/resources/
└── application.properties       # Database configuration
pom.xml
```

## Setup

1. Make sure MySQL is running
2. Update the username/password in `application.properties` if different from `root` / your password
3. Run `LibraryApplication.java` — the database and tables are created automatically on first run

## Authentication

Two hardcoded users for demo purposes (HTTP Basic Auth):

| Username | Password  | Role  |
|----------|-----------|-------|
| admin    | adminpass | ADMIN |
| user     | userpass  | USER  |

## API Endpoints

| Method | Endpoint                          | Access      | Description                     |
|--------|------------------------------------|-------------|----------------------------------|
| GET    | `/api/students`                   | Any role    | View all students                |
| GET    | `/api/students/{id}`               | Any role    | View one student                 |
| POST   | `/api/students`                   | Admin only  | Add a new student                |
| DELETE | `/api/students/{id}`               | Admin only  | Delete a student                 |
| GET    | `/api/books`                       | Any role    | View all books                   |
| GET    | `/api/books/available`             | Any role    | View only available books        |
| POST   | `/api/books`                       | Admin only  | Add a new book                   |
| POST   | `/api/issues/issue?bookId=&studentId=` | Any role | Issue a book to a student     |
| POST   | `/api/issues/return?bookId=`       | Any role    | Return a book                    |
| GET    | `/api/issues/history`              | Any role    | Full issue/return history        |
| GET    | `/api/issues/history/book/{id}`     | Any role    | History for a specific book      |
| GET    | `/api/issues/history/student/{id}`  | Any role    | History for a specific student   |

## Testing with Postman

1. Set the request method (GET/POST) and URL as shown above
2. Under **Authorization** tab, select **Basic Auth** and enter one of the credentials above
3. For POST requests with a body (add student/book), use the **Body** tab → **raw** → **JSON**, e.g.:
```json
{
  "name": "Rahul",
  "age": 20,
  "department": "CSE",
  "email": "rahul@gmail.com"
}
```

## About

Built as part of the **CodeVedX** Java Development Internship — Level 3.
