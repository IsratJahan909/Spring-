package com.example.Relational.Database.Controller;


import com.example.Relational.Database.entity.Product;
import com.example.Relational.Database.service.FileStorageService;
import com.example.Relational.Database.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final FileStorageService fileStorageService;

    @PostMapping(value = "/create", consumes = "multipart/form-data")
    public ResponseEntity<Product> createProduct(
            @RequestPart("product") Product product,
            @RequestPart("files") MultipartFile[] files
    ) throws Exception {

        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            urls.add(fileStorageService.saveFile(file));
        }

        product.setImagesUrls(urls);
        return ResponseEntity.ok(productService.save(product));
    }

    // ✅ GET ALL
    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    // ✅ GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body(null));
    }

    // ✅ UPDATE Product + Optional New Files
    @PutMapping(value = "/update/{id}", consumes = "multipart/form-data")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestPart("product") Product updatedProduct,
            @RequestPart(value = "files", required = false) MultipartFile[] files
    ) throws Exception {

        Optional<Product> optional = productService.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Product not found");
        }

        Product existing = optional.get();

        existing.setName(updatedProduct.getName());
        existing.setPrice(updatedProduct.getPrice());

        // If new images uploaded
        if (files != null && files.length > 0) {
            List<String> newUrls = new ArrayList<>();
            for (MultipartFile file : files) {
                newUrls.add(fileStorageService.saveFile(file));
            }
            existing.getImagesUrls().addAll(newUrls);
        }

        return ResponseEntity.ok(productService.save(existing));
    }


    @DeleteMapping("/{productId}/image")
    public ResponseEntity<?> deleteImage(
            @PathVariable Long productId,
            @RequestParam String imageUrl
    ) throws Exception {

        Optional<Product> optional = productService.findById(productId);
        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Product not found");
        }

        Product product = optional.get();

        if (!product.getImagesUrls().remove(imageUrl)) {
            return ResponseEntity.status(404).body("Image not found in product");
        }

        fileStorageService.deleteFile(imageUrl);

        productService.save(product);
        return ResponseEntity.ok("Image deleted successfully");
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) throws Exception {
        Optional<Product> optional = productService.findById(id);

        if (optional.isEmpty()) {
            return ResponseEntity.status(404).body("Product not found");
        }

        Product product = optional.get();

        // Delete image files
        for (String img : product.getImagesUrls()) {
            fileStorageService.deleteFile(img);
        }

        productService.delete(product);
        return ResponseEntity.ok("Product deleted successfully");}
}
