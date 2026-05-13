package com.darshan.redis_demo_app.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.darshan.redis_demo_app.dto.ProductRequest;
import com.darshan.redis_demo_app.entity.Product;
import com.darshan.redis_demo_app.repository.ProductRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        log.info(">>> Fetching ALL products from DATABASE"); // visible in logs
        simulateSlowDbCall();
        return productRepository.findAll();
    }

    public Product getProductById(Long id) {
        log.info(">>> Fetching product [{}] from DATABASE", id); // visible in logs
        simulateSlowDbCall();
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
    }

    public Product createProduct(ProductRequest request) {
        log.info(">>> Creating new product: {}", request.getName());
        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .stock(request.getStock())
                .category(request.getCategory())
                .build();
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequest request) {
        log.info(">>> Updating product [{}] in DATABASE", id);
        Product existing = getProductById(id);
        existing.setName(request.getName());
        existing.setDescription(request.getDescription());
        existing.setPrice(request.getPrice());
        existing.setStock(request.getStock());
        existing.setCategory(request.getCategory());
        return productRepository.save(existing);
    }

    public void deleteProduct(Long id) {
        log.info(">>> Deleting product [{}] from DATABASE", id);
        productRepository.deleteById(id);
    }

    /**
     * Simulates a slow database call (e.g., complex query, remote DB).
     * Used purely for demo purposes to show latency WITHOUT caching.
     * We will remove this once caching is introduced.
     */
    private void simulateSlowDbCall() {
        try {
            Thread.sleep(500); // 500ms artificial delay
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
