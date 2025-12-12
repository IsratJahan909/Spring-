package com.abc.springRestCurd.service;


import com.abc.springRestCurd.entity.Products;
import com.abc.springRestCurd.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

//    @Autowired
//    ProductRepository productRepository;

    //Constractor Injection
    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Products> getAll() {
        return productRepository.findAll();
    }

    public Optional<Products> getById(Long id) {
        return productRepository.findById(id);
    }

    public Products create (Products products) {
        return  productRepository.save(products);
    }

    public Products update(Long id, Products UpdatedProduct) {
       Products existing = productRepository.findById(id)
               .orElseThrow(() -> new RuntimeException("Product not found with ID: " + id));

       existing.setName(UpdatedProduct.getName());
       existing.setDescription(UpdatedProduct.getDescription());
       existing.setPrice(UpdatedProduct.getPrice());
       existing.setStockQuantity(UpdatedProduct.getStockQuantity());

      return productRepository.save(existing);
    }
}

