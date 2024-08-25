package com.ecom_proj.ecomproj.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecom_proj.ecomproj.models.Products;
import com.ecom_proj.ecomproj.services.ProductService;
// import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

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

    @PostMapping("/products")
    public ResponseEntity<?> addProduct(@RequestPart Products product, @RequestPart MultipartFile imageFile) {
        try {
            Products product1 = service.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/products/{prodId}/image")
    public ResponseEntity<byte[]> getImgById(@PathVariable int prodId) {
        Products prod2 = service.getOneProduct(prodId);
        byte[] imgFile = prod2.getImageData();
        return ResponseEntity.ok()
                .contentType(MediaType.valueOf(prod2.getImageType()))
                .body(imgFile);
    }

    @PutMapping("/products/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id, @RequestPart Products product,
            @RequestPart MultipartFile imageFile) {

        Products product1 = null;
        try {
            product1 = service.updateProduct(id, product, imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }
        if (product1 != null) {
            return new ResponseEntity<>("updated", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update", HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<String> deleteProd(@PathVariable int id) {
        Products prod4 = service.getOneProduct(id);
        if (prod4 != null) {
            service.deleteProd(id);
            return new ResponseEntity<>("Deleted", HttpStatus.OK);
        } else
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);

    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Products>> searchProducts(@RequestParam String keyword) {
        List<Products> prodx = service.searchProducts(keyword);
        return new ResponseEntity<>(prodx, HttpStatus.OK);
    }
}
