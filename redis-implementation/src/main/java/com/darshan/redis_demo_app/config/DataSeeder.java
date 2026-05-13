package com.darshan.redis_demo_app.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.darshan.redis_demo_app.entity.Product;
import com.darshan.redis_demo_app.repository.ProductRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {

    private final ProductRepository productRepository;

    @Override
    public void run(String... args) {
        if (productRepository.count() == 0) {
            log.info("Seeding product data...");
            productRepository.saveAll(List.of(
                    Product.builder().name("MacBook Pro 14").description("Apple").build(),
                    Product.builder().name("Dell XPS 15").description("Intel Core").build(),
                    Product.builder().name("Sony WH-1000XM5").description("Industry").build(),
                    Product.builder().name("Samsung 4K Monitor").description("27 inch").build(),
                    Product.builder().name("Logitech MX Master 3").description("Mouse").build(),
                    Product.builder().name("Keychron K2 Pro").description("Wireless").build(),
                    Product.builder().name("iPad Pro 12.9").description("M2 chip").build(),
                    Product.builder().name("LG 34\" UltraWide").description("34-inch").build(),
                    Product.builder().name("Anker USB-C Hub").description("7-in-1").build(),
                    Product.builder().name("iPhone 15 Pro").description("Titanium").build()));
            log.info("Seeded {} products successfully.", productRepository.count());
        }
    }
}