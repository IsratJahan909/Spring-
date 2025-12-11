package com.example.Relational.Database.repository;


import com.example.Relational.Database.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
