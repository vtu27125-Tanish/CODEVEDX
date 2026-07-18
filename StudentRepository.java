package com.codevedx.library.repository;

import com.codevedx.library.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

// Extending JpaRepository gives us save(), findAll(), findById(), deleteById() for free
public interface StudentRepository extends JpaRepository<Student, Long> {
}
