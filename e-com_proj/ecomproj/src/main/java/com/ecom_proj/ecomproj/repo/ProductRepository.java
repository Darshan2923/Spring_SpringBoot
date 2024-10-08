package com.ecom_proj.ecomproj.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.ecom_proj.ecomproj.models.Products;

@Repository
public interface ProductRepository extends JpaRepository<Products, Integer> {

    @Query("SELECT p FROM Products p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%',:keyword,'%')) OR LOWER(p.description) LIKE LOWER(CONCAT('%',:keyword,'%')) OR LOWER(p.brand) LIKE LOWER(CONCAT('%',:keyword,'%')) OR LOWER(p.category) LIKE LOWER(CONCAT('%',:keyword,'%'))")
    List<Products> searchProducts(String keyword);
}
