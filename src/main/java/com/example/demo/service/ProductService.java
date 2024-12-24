package com.example.demo.service;

import com.example.demo.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> findAll();

    Product findByName(String productName);

    Product addProduct(Product product);

}
