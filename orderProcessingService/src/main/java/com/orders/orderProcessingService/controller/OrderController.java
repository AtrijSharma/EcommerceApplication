package com.orders.orderProcessingService.controller;

import com.orders.orderProcessingService.Exception.OrderNotFoundException;
import com.orders.orderProcessingService.Security.ValidateJwtToken;
import com.orders.orderProcessingService.entity.Order;
import com.orders.orderProcessingService.service.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private static final Logger logger = LogManager.getLogger(OrderController.class);
    private final OrderService orderService;
    @Autowired
    private ValidateJwtToken validateToken;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Order>> getAllOrders(HttpServletRequest request) {
        if (validateToken.validateToken(request)) {
            logger.info("Fetching all orders");
            List<Order> orders = orderService.getAllOrders();
            return new ResponseEntity<>(orders, HttpStatus.OK);
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id, HttpServletRequest request) {
        if (validateToken.validateToken(request)) {
            logger.info("Fetching order by ID: {}", id);
            Order order = orderService.getOrder(id);
            return new ResponseEntity<>(order, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Order(), HttpStatus.OK);
    }

    @PostMapping("/")
    public ResponseEntity<String> createOrder(@RequestBody Order order, HttpServletRequest request) {
        if (validateToken.validateToken(request)) {
            logger.info("Creating order: {}", order);
            orderService.createOrder(order);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order created successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order can not be created due to bad request credentials");

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateOrder(@PathVariable Long id, @RequestBody Order order, HttpServletRequest request) {
        if (validateToken.validateToken(request)) {
            logger.info("Updating order with ID: {}", id);
            orderService.updateOrder(id, order);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order updated successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order can not be updated due to bad request credentials");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable Long id, HttpServletRequest request) {
        if (validateToken.validateToken(request)) {
            logger.info("Deleting order with ID: {}", id);
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.CREATED).body("Order Deleted successfully");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Order can not be deleted due to bad request credentials");
    }

    @ExceptionHandler(OrderNotFoundException.class)
    public ResponseEntity<String> handleOrderNotFoundException(OrderNotFoundException ex) {
        logger.error("An error occurred: {}", ex.getMessage());
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

