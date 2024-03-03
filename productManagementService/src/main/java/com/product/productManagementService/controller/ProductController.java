package com.product.productManagementService.controller;

import com.product.productManagementService.Security.ValidateJwtToken;
import com.product.productManagementService.entity.Product;
import com.product.productManagementService.service.ProductService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;
    @Autowired
    private ValidateJwtToken validateToken;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getAllProducts(HttpServletRequest request) {

        if (validateToken.validateToken(request)) {
            List<Product> products = productService.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id, HttpServletRequest request) {
        if (validateToken.validateToken(request)) {
            Product product = productService.getProduct(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Product(), HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/")
    public ResponseEntity<String> createProduct(@RequestBody Product product, HttpServletRequest request) {
        if (validateToken.validateToken(request)) {
            productService.createProduct(product);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product created successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product can not be created due to bad request credentials");

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product product, HttpServletRequest request) {
        if (validateToken.validateToken(request)) {
            try {
                productService.updateProduct(id, product);
                return ResponseEntity.status(HttpStatus.CREATED).body("Product updated successfully");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid version or data for updation");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product can not be updated due to bad request credentials");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id, HttpServletRequest request) {
        if (validateToken.validateToken(request)) {
            productService.deleteProduct(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Product deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product can not be deleted due to bad request credentials");
    }


}
