package com.abc.springRestCurd.repository;

import com.abc.springRestCurd.entity.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Products, Long> {

}
