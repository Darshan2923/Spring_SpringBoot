package com.ecom_proj.ecomproj.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom_proj.ecomproj.models.Products;

@Repository
public interface ProductRepo extends JpaRepository<Products, Integer> {

}
