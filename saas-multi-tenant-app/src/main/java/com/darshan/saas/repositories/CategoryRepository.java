package com.darshan.saas.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darshan.saas.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {
    Optional<Category> findByNameIgnoreCase(String name);
}
