package com.example.Relational.Database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "ORDERS")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String product;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    // getters & setters
}
