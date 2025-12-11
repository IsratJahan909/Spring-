package com.example.Relational.Database.repository;


import com.example.Relational.Database.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
