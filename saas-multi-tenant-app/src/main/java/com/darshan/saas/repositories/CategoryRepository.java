package com.darshan.saas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.darshan.saas.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, String> {

}
