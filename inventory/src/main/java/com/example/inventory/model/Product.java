package com.example.inventory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Product {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer product_id;
    private String product_name;
    private Integer quantity;
    private Integer price;


    public Product(Integer product_id,String product_name,Integer quantity,Integer price){
        this.product_id = product_id;
        this.product_name= product_name;
        this.quantity= quantity;
        this.price= price;

    }
}
