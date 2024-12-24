package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Product> findProductByName(String name);

    List<Product> findProductsByAvailableIsTrue();

    Product add(Product product);

    Product update(Product product);

    @Query("DELETE FROM Product p WHERE p.id=:id")
    Product deleteProductById(Long id);
}
