package com.ecom_proj.ecomproj.models;

import java.math.BigDecimal;
import java.util.Date;

// import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Products {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String desc;
    private String brand;
    private BigDecimal price;
    private String category;

    // Already handled in frontend
    // @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "DD-MM-YYYY")
    private Date release_date;
    private boolean available;
    private int quantity;

    // Image Entities

}
