package com.example.Relational.Database.repository;


import com.example.Relational.Database.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
