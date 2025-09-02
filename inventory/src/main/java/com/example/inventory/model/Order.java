package com.example.inventory.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "orders")
public class Order {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer order_id;
    private Integer product_id;
    private Integer quantity;
    private String order_date;
}
