package com.example.Relational.Database.repository;


import com.example.Relational.Database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {}

