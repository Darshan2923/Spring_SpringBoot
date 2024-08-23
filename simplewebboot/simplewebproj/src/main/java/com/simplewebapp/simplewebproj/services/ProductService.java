package com.simplewebapp.simplewebproj.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.simplewebapp.simplewebproj.models.Product;

@Service
public class ProductService {
    List<Product> products = new ArrayList<>(
            Arrays.asList(new Product(101, "Iphone", 50000), new Product(102, "Camera", 12000)));

    public List<Product> getProducts() {
        return products;
    }

    public Product getProdById(int prodId) {
        return products.stream()
                .filter(p -> p.getProdId() == prodId)
                .findFirst().get();
    }

    public void addProduct(Product prod) {
        products.add(prod);
    }

    public void updateProd(Product prod) {
        int index = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProdId() == prod.getProdId()) {
                index = i;
                break;
            }
        }
        products.set(index, prod);
    }

    public void delProd(int prodId) {
        int index = 0;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getProdId() == prodId) {
                index = i;
                break;
            }
        }
        products.remove(index);
    }

}
