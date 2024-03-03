package com.orders.orderProcessingService;

import com.orders.orderProcessingService.entity.Order;
import com.orders.orderProcessingService.repository.OrderRepository;
import com.orders.orderProcessingService.service.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
class OrderProcessingServiceApplicationTests {

	@Mock
	private OrderRepository orderRepository;


	@InjectMocks
	private OrderService orderProcessingService;
	@Test
	public void testGetAllOrders() {
		Order order1 = new Order();
		order1.setId(1L);
		order1.setTotalAmount(100.00);

		Order order2 = new Order();
		order2.setId(2L);
		order2.setTotalAmount(200.00);

		List<Order> orderList = new ArrayList<>();
		orderList.add(order1);
		orderList.add(order2);

		when(orderRepository.findAll()).thenReturn(orderList);

		List<Order> orders = orderProcessingService.getAllOrders();

		assertEquals(2, orders.size());
		assertEquals(1L, orders.get(0).getId());
		assertEquals(2L, orders.get(1).getId());
	}

	@Test
	public void testGetOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setTotalAmount(100.00);

		when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));

		Order foundOrder = orderProcessingService.getOrder(1L);

		assertEquals(100.00, foundOrder.getTotalAmount());
	}

	@Test
	public void testCreateOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setTotalAmount(100.00);

		when(orderRepository.save(order)).thenReturn(order);

		orderProcessingService.createOrder(order);
	}

	@Test
	public void testUpdateOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setTotalAmount(100.00);

		when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));
		when(orderRepository.save(order)).thenReturn(order);

		orderProcessingService.updateOrder(1L, order);

	}

	@Test
	public void testDeleteOrder() {
		Order order = new Order();
		order.setId(1L);
		order.setTotalAmount(100.00);

		when(orderRepository.findById(1L)).thenReturn(java.util.Optional.of(order));

		orderProcessingService.deleteOrder(1L);

		}

}
