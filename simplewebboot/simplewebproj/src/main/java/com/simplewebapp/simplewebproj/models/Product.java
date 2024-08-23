package com.simplewebapp.simplewebproj.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor // This will add the no-argument constructor
public class Product {
    @Id
    private int prodId;
    private String prodName;
    private int price;

}
