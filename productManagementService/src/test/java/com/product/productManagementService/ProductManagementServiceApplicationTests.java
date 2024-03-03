package com.product.productManagementService;

import com.product.productManagementService.entity.Product;
import com.product.productManagementService.repository.ProductRepository;
import com.product.productManagementService.service.ProductService;
import com.product.productManagementService.service.impl.ProductServiceImpl;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
class ProductManagementServiceApplicationTests {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productManagementService;

	@Test
	public void testGetAllProducts() throws JSONException {
		Product product1 = new Product();
		product1.setId(1L);
		product1.setName("Product 1");

		Product product2 = new Product();
		product2.setId(2L);
		product2.setName("Product 2");

		List<Product> productList = new ArrayList<>();
		productList.add(product1);
		productList.add(product2);

		when(productRepository.findAll()).thenReturn(productList);

		assertEquals("2", String.valueOf(productList.size()),true);
		assertEquals("Product 1",productList.get(0).getName(),true);
		assertEquals("Product 2", productList.get(1).getName(),true);
	}


	@Test
	public void testGetProductById() throws JSONException {
		Product product = new Product();
		product.setId(1L);
		product.setName("Product 1");

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		Product foundProduct = productManagementService.getProduct(1L);

		assertEquals("Product 1", foundProduct.getName(),true);
	}

	@Test
	public void testCreateProduct() {
		Product product = new Product();
		product.setName("New Product");


		productManagementService.createProduct(product);

	}

	@Test
	public void testUpdateProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setName("Product 1");

		when(productRepository.findById(1L)).thenReturn(Optional.of(product));

		Product updatedProduct = new Product();
		updatedProduct.setId(1L);
		updatedProduct.setName("Updated Product 1");


		productManagementService.updateProduct(1L, updatedProduct);

	}

	@Test
	public void testDeleteProduct() {
		Product product = new Product();
		product.setId(1L);
		product.setName("Product 1");

		productManagementService.deleteProduct(1L);

	}
}
