package com.example.Relational.Database.repository;


import com.example.Relational.Database.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}