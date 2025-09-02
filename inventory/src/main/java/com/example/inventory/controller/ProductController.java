package com.example.inventory.controller;

import com.example.inventory.model.Order;
import com.example.inventory.model.Product;
import com.example.inventory.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
//@RequestMapping("/inventory")
@CrossOrigin("*")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/getallproducts")
    public List<Product> getallproduct(){

        return productService.getallproduct();
    }

    @GetMapping("/getproduct/{product_id}")
    public Optional<Product> getproduct(@PathVariable("product_id")Integer product_id  ){

        return productService.getproduct(product_id);
    }
    @PostMapping("/add")
    public Product addproduct(@RequestBody Product product){

        return productService.addproduct(product);
    }
    @PutMapping("/edit/{product_id}")
    public void editproduct(@PathVariable("product_id")Integer product_id , @RequestBody Product product ){

        productService.editproduct(product_id, product);
    }
    @DeleteMapping("/delete/{product_id}")
    public void deleteproduct(@PathVariable("product_id") Integer product_id) {

       productService.deleteproduct(product_id);
    }

    @PostMapping("/order")
    public String placeorder(@RequestParam Integer product_id, @RequestParam Integer quantity) {
        return productService.placeorder(product_id,quantity);
    }

    @PostMapping("/addallproducts")
    public List<Product> addallproducts(@RequestBody List<Product> product){
        return productService.addallproduct(product);
    }

    @GetMapping("/getallorders")
    public List<Order> getAllOrders() {
        return productService.getAllOrders();
    }
}
