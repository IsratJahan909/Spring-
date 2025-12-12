package com.abc.springRestCurd.entity;


import com.abc.springRestCurd.enums.Catagory;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import javax.xml.catalog.Catalog;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name ="products")
@Data
public class Products {

 @Id
 @GeneratedValue(strategy = GenerationType.IDENTITY)
 private Long id;

 @Column(nullable = false)
 private String name;

 @Column(length = 2000)
 private String description;

 @Column(nullable = false)
    private Double price;

 private Integer stockQuantity;

 @Enumerated(EnumType.STRING)
    @Column(length = 50)
    private Catagory catalog;

 private Boolean available = true;

 private LocalDate releaseDate;

 @Column(updatable = false)
 private LocalDateTime createdAt;


    private LocalDateTime updatedAt;



 @ElementCollection
 @CollectionTable(name = "product_images",
         joinColumns = @JoinColumn(name = "product_id"))
 @Column(name = "image_url")
 private List<String> imageUrl;

 private Double rating;

 @PrePersist
    public  void  prePersist() {
     createdAt = LocalDateTime.now();
 }

    @PreUpdate
    public  void  preUpdate() {
      updatedAt = LocalDateTime.now();
    }







}
