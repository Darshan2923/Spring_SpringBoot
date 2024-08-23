package com.simplewebapp.simplewebproj.services;

// import java.util.ArrayList;
// import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simplewebapp.simplewebproj.models.Product;
import com.simplewebapp.simplewebproj.repo.ProductRepo;

@Service
public class ProductService {

    @Autowired
    ProductRepo repo;
    // List<Product> products = new ArrayList<>(
    // Arrays.asList(new Product(101, "Iphone", 50000), new Product(102, "Camera",
    // 12000)));

    public List<Product> getProducts() {
        return repo.findAll();
        // return products;
    }

    public Product getProdById(int prodId) {
        return repo.findById(prodId).orElse(new Product(prodId, null, prodId));
        // return products.stream()
        // .filter(p -> p.getProdId() == prodId)
        // .findFirst().get();
    }

    public void addProduct(Product prod) {
        repo.save(prod);
        // products.add(prod);
    }

    public void updateProd(Product prod) {
        repo.save(prod);
        // int index = 0;
        // for (int i = 0; i < products.size(); i++) {
        // if (products.get(i).getProdId() == prod.getProdId()) {
        // index = i;
        // break;
        // }
        // }
        // products.set(index, prod);
    }

    public void delProd(int prodId) {
        repo.deleteById(prodId);
        // int index = 0;
        // for (int i = 0; i < products.size(); i++) {
        // if (products.get(i).getProdId() == prodId) {
        // index = i;
        // break;
        // }
        // }
        // products.remove(index);
    }

}
