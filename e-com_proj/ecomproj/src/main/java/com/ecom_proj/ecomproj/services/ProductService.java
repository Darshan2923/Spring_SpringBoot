package com.ecom_proj.ecomproj.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom_proj.ecomproj.models.Products;
import com.ecom_proj.ecomproj.repo.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo repo;

    public List<Products> getAllProducts() {
        return repo.findAll();
    }

    public Products getOneProduct(int prodId) {
        return repo.findById(prodId).orElse(null);
    }

}
