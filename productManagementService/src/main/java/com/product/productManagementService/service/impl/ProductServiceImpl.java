package com.product.productManagementService.service.impl;


import com.product.productManagementService.entity.Product;
import com.product.productManagementService.repository.ProductRepository;
import com.product.productManagementService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {
    private static final Logger logger = LogManager.getLogger(ProductService.class);

    @Autowired
    ProductRepository productRepository;


    @Override
    public List<Product> getAllProducts() {


        logger.info("Fetching all products");
        return productRepository.findAll();
    }

    @Override
    public Product getProduct(Long id) {
        logger.info("Fetching product by ID: {}", id);
        Optional<Product> productOptional = productRepository.findById(id);
        return productOptional.orElse(null);
    }

    @Override
    public void createProduct(Product product) {
        logger.info("Creating product: {}", product);
        productRepository.save(product);
    }

    @Override
    public void updateProduct(Long id, Product product) {
        logger.info("Updating product with ID: {}", id);
        Product existingProduct = getProduct(id);
        if (existingProduct != null) {
            product.setId(id);
            productRepository.save(product);
        }
    }

    @Override
    public void deleteProduct(Long id) {
        logger.info("Deleting product with ID: {}", id);
        productRepository.deleteById(id);
    }


}
