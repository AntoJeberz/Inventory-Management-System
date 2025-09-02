package com.example.inventory.service;

import com.example.inventory.model.Order;
import com.example.inventory.model.Product;
import com.example.inventory.repository.OrderRepository;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    ProductRepository repository;

    @Autowired
    OrderRepository orderRepository;

    public List<Product> getallproduct() {
        return repository.findAll();
    }

    public Optional<Product> getproduct(Integer product_Id) {
        return repository.findById(product_Id);
    }

    public Product addproduct(Product product) {
        return repository.save(product);
    }

    public void editproduct(Integer productId, Product updatedProduct) {
        Optional<Product> existingProductOpt = repository.findById(productId);

        if (existingProductOpt.isPresent()) {
            Product existingProduct = existingProductOpt.get();

            existingProduct.setProduct_name(updatedProduct.getProduct_name());
            existingProduct.setPrice(updatedProduct.getPrice());


            int newQuantity = existingProduct.getQuantity() + updatedProduct.getQuantity();
            existingProduct.setQuantity(newQuantity);

            repository.save(existingProduct);
        }
    }


    public void deleteproduct(Integer productId) {
        repository.deleteById(productId);
    }

    public String placeorder(Integer productId, Integer quantity) {
        Optional<Product> productOptional = repository.findById(productId);

        if (productOptional.isEmpty()) {
            return "Product not found!";
        }

        Product product = productOptional.get();

        if (product.getQuantity() < quantity) {
            return "Insufficient stock!";
        }


        product.setQuantity(product.getQuantity() - quantity);
        repository.save(product);


        Order order = new Order();
        order.setProduct_id(product.getProduct_id());
        order.setQuantity(quantity);
        order.setOrder_date(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        orderRepository.save(order);

        return "Order placed successfully ";
    }

    public List<Product> addallproduct(List<Product> product) {
        return repository.saveAll(product);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }
}
