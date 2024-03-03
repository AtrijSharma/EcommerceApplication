package com.userAuth;

import com.userAuth.controller.UserController;
import com.userAuth.entity.User;
import com.userAuth.service.UserService;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;
import static org.skyscreamer.jsonassert.JSONAssert.assertEquals;

@SpringBootTest
class UserAuthenticationServiceApplicationTests {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Test
	public void testRegister() throws JSONException {
		User user = new User();
		user.setUsername("testuser");
		user.setPassword("password");

		doNothing().when(userService).register(user);

		ResponseEntity<String> responseEntity = userController.register(user);
		assertEquals("User registered successfully",responseEntity.getBody().toString(),true);
	}

	@Test
	public void testLogin() throws JSONException {
		User user = new User();
		user.setUsername("testuser");
		user.setPassword("password");

		when(userService.login(user)).thenReturn("token");

		String token = userController.login(user);

		assertEquals("token", token,true);

		verify(userService, times(1)).login(user);
	}



}
