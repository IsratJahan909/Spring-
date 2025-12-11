package com.example.Relational.Database.entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Data
@Getter
@Setter
@Entity
@Builder

public class Product {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private  Long id;

private  String name;
private Double price;

@ElementCollection
    private List<String> imagesUrls;  //store image paths


}
