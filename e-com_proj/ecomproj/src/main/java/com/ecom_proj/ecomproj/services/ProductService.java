package com.ecom_proj.ecomproj.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ecom_proj.ecomproj.models.Products;
import com.ecom_proj.ecomproj.repo.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    public List<Products> getAllProducts() {
        return repo.findAll();
    }

    public Products getOneProduct(int prodId) {
        return repo.findById(prodId).orElse(null);
    }

    public Products addProduct(Products product, MultipartFile imageFile) throws IOException {
        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());

        return repo.save(product);
    }

    public Products updateProduct(int id, Products product, MultipartFile imageFile) throws IOException {

        product.setImageName(imageFile.getOriginalFilename());
        product.setImageType(imageFile.getContentType());
        product.setImageData(imageFile.getBytes());
        return repo.save(product);
    }

    public void deleteProd(int id) {
        repo.deleteById(id);
    }

    public List<Products> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }

}
