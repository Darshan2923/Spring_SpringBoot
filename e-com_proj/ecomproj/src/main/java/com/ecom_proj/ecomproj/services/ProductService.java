package com.ecom_proj.ecomproj.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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

    public Products addProduct(Products product, MultipartFile imgFile) throws IOException {
        product.setImageName(imgFile.getOriginalFilename());
        product.setImageType(imgFile.getContentType());
        product.setImageData(imgFile.getBytes());
        return repo.save(product);
    }

    public Products updateProd(int prodId, Products product, MultipartFile imgFile) throws IOException {
        product.setImageData(imgFile.getBytes());
        product.setImageName(imgFile.getOriginalFilename());
        product.setImageType(imgFile.getContentType());
        return repo.save(product);
    }

    public void deleteProd(int id) {
        repo.deleteById(id);
    }

    public List<Products> searchProducts(String keyword) {
        return repo.searchProducts(keyword);
    }

}
