# Student Management System

A simple console-based Student Management System built in Java, using JDBC to connect to a MySQL database. Built as part of the CodeVedX Java Development Internship (Level 2 - Task 4).

## Features

- **Add Student** — insert a new student record (name, age, department, email)
- **View All Students** — display all student records from the database
- **Delete Student** — remove a student record by ID

## Tech Stack

- Java
- JDBC
- MySQL

## Project Structure

```
StudentManagementSystem/
├── Main.java          # Console menu and program entry point
├── Student.java        # Student model class (POJO)
├── StudentDAO.java      # Database access logic (CRUD operations)
├── DBConnection.java    # Handles the MySQL connection
├── database.sql         # SQL schema for the students table
└── README.md
```

## Database Setup

Run the following in MySQL before starting the app:

```sql
USE student_db;

CREATE TABLE IF NOT EXISTS students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    age INT NOT NULL,
    department VARCHAR(100) NOT NULL,
    email VARCHAR(150)
);
```

## How to Run

1. Set up the database using `database.sql`
2. Update the MySQL username/password in `DBConnection.java` if needed
3. Compile and run `Main.java`
4. Use the on-screen menu to add, view, or delete student records

## Sample Menu

```
===== STUDENT MANAGEMENT SYSTEM =====
1. Add Student
2. View All Students
3. Delete Student
4. Exit
```

## About

Built as part of the **CodeVedX** Java Development Internship.