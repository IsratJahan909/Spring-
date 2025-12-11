package com.example.Relational.Database.repository;


import com.example.Relational.Database.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
