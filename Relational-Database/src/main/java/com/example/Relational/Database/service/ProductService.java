package com.example.Relational.Database.service;
import com.example.Relational.Database.entity.Product;
import com.example.Relational.Database.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }

    public java.util.List<Product> findAll() {
        return productRepository.findAll();


}

}
