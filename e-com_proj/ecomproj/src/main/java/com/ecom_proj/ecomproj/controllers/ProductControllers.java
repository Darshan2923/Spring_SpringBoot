package com.ecom_proj.ecomproj.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecom_proj.ecomproj.models.Products;
import com.ecom_proj.ecomproj.services.ProductService;
// import org.springframework.web.bind.annotation.RequestParam;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductControllers {

    @Autowired
    private ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Products>> getAllProducts() {
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/products/{prodId}")
    public ResponseEntity<Products> getOneProduct(@PathVariable int prodId) {
        Products product = service.getOneProduct(prodId);

        if (product != null)
            return new ResponseEntity<>(product, HttpStatus.OK);

        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
