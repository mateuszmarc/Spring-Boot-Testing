package com.example.demo.repository;

import com.example.demo.model.Product;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
class ProductRepositoryTest {

    @Autowired
    EntityManager entityManager;

    @Autowired
    ProductRepository productRepository;

    @BeforeEach
    public void setupTestData() {

      Product milk = new Product("milk", "fresh milk", true);
      Product carrot = new Product("carrot", "fresh carrot", true);
      Product cheese = new Product("cheese", "fresh cheese", true);
      Product egg = new Product("egg", "fresh egg", false);

      entityManager.persist(milk);
      entityManager.persist(carrot);
      entityManager.persist(cheese);
      entityManager.persist(egg);
    }

    @AfterEach
    public void tearDownDate() {
       Query query = entityManager.createQuery("DELETE FROM Product p");
       query.executeUpdate();
    }

    @Test
    void testFindProductByName_whenFindByProductInDatabase_ShouldBePresent() {

        Optional<Product> optionalProduct = productRepository.findProductByName("egg");

        assertAll(
                () -> assertThat(optionalProduct).isPresent(),
                () -> assertThat(optionalProduct.get().getName()).isEqualTo("egg")
        );
    }

    @Test
    void testFindProductByName_whenFindByProductInDatabase_ShouldBeEmpty() {

        Optional<Product> optionalProduct = productRepository.findProductByName("apple");
        assertThat(optionalProduct).isEmpty();
    }

    @Test
    void testFindProductsByAvailableIsTrue_whenFindAvailableProducts_thenReturnNotEmptyList() {

        List<Product> available = productRepository.findProductsByAvailableIsTrue();

        assertThat(available).hasSize(3);
    }
}