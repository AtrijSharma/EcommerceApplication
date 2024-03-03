package com.product.productManagementService.service;

import com.product.productManagementService.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    Product getProduct(Long id);

    void createProduct(Product product);

    void updateProduct(Long id, Product product);

    void deleteProduct(Long id);
}
