package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @InjectMocks
    ProductServiceImpl productService;

    @Mock
    ProductRepository productRepository;


    @Test
    void testFindAll_whenFindAll_thenReturnListNotEmpty() {

        List<Product> products = new ArrayList<>(List.of(
                new Product("carrot", "fresh carrot", true),
                new Product("milk", "fresh milk", true)
        ));

        when(productRepository.findAll()).thenReturn(products);

        List<Product> returnedList = productService.findAll();

        verify(productRepository).findAll();
        assertThat(returnedList).hasSize(2);
    }

    @Test
    void testFindByName_thenReturnProduct() {
        Product carrot = new Product("carrot", "fresh carrot", true);

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        when(productRepository.findProductByName("carrot")).thenReturn(Optional.of(carrot));

        Product product = productService.findByName("carrot");

        verify(productRepository).findProductByName(argumentCaptor.capture());

        String usedArgument = argumentCaptor.getValue();

        assertThat(usedArgument).isEqualTo("carrot");
        assertThat(product).isEqualTo(carrot);
    }

    @Test
    void testFindByName_thenReturnNull() {

        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        when(productRepository.findProductByName("carrot")).thenReturn(Optional.empty());

        Product product = productService.findByName("carrot");

        verify(productRepository).findProductByName(argumentCaptor.capture());

        String usedArgument = argumentCaptor.getValue();

        assertThat(usedArgument).isEqualTo("carrot");
        assertThat(product).isNull();
    }

    @Test
    void testAddProduct_whenAddingProduct_thenReturnProduct() {
        Product carrot = new Product("carrot", "fresh carrot", true);

        ArgumentCaptor<Product> argumentCaptor = ArgumentCaptor.forClass(Product.class);

        when(productRepository.add(carrot)).thenReturn(carrot);
        Product addedProduct = productService.addProduct(carrot);

        verify(productRepository).add(argumentCaptor.capture());
        Product productToVerify = argumentCaptor.getValue();

        assertThat(productToVerify).isEqualTo(carrot);
        assertThat(addedProduct).isEqualTo(carrot);
    }
}