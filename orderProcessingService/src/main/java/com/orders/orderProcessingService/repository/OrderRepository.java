package com.orders.orderProcessingService.repository;


import com.orders.orderProcessingService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
