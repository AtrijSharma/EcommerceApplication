package com.orders.orderProcessingService.service;

import com.orders.orderProcessingService.Exception.OrderNotFoundException;
import com.orders.orderProcessingService.entity.Order;
import com.orders.orderProcessingService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private static final Logger logger = LogManager.getLogger(OrderService.class);


    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAllOrders() {
        logger.info("Fetching all orders");
        return orderRepository.findAll();
    }

    public Order getOrder(Long id) {
        logger.info("Fetching order by ID: {}", id);
        return orderRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException("Order not found with id: " + id));
    }

    public void createOrder(Order order) {
        logger.info("Creating order: {}", order);
        orderRepository.save(order);
    }

    public void updateOrder(Long id, Order order) {
        logger.info("Updating order with ID: {}", id);
        Order existingOrder = getOrder(id);
        existingOrder.setUserId(order.getUserId());
        existingOrder.setTotalAmount(order.getTotalAmount());
        orderRepository.save(existingOrder);
    }

    public void deleteOrder(Long id) {
        logger.info("Deleting order with ID: {}", id);
        orderRepository.deleteById(id);
    }
}
