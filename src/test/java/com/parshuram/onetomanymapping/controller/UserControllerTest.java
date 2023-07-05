package com.parshuram.onetomanymapping.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.parshuram.onetomanymapping.entity.ApiResponse;
import com.parshuram.onetomanymapping.entity.User;
import com.parshuram.onetomanymapping.service.UserService;

@SpringBootTest
public class UserControllerTest {

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Test
	void testCreateUser() {

		User user = new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l);

		when(userService.createUser(user)).thenReturn(user);

		ResponseEntity<User> responseEntity = userController.createUser(user);

		assertEquals(user, responseEntity.getBody());

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	void testUpdateUser() {

		Integer userId = 1;

		User user = new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l);

		when(userService.updateUser(user, userId)).thenReturn(user);

		ResponseEntity<User> responseEntity = userController.updateUser(user, userId);

		assertEquals(user, responseEntity.getBody());

		assertEquals(HttpStatus.ACCEPTED, responseEntity.getStatusCode());

		assertEquals(202, responseEntity.getStatusCodeValue());

		assertEquals(userId, responseEntity.getBody().getUserId());

		assertEquals("Parshuram Bhosekar", responseEntity.getBody().getName());

		assertEquals("Sangli", responseEntity.getBody().getCity());

		assertEquals("p@gmail.com", responseEntity.getBody().getEmail());

		assertEquals(9623771726l, responseEntity.getBody().getMobileNumber());
	}

	@Test
	void testGetAllUser() {

		List<User> userList = new ArrayList<>();
		userList.add(new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));
		userList.add(new User(2, "Sumit Pawar", "Sangli", "s@gmail.com", 9307304360l));
		userList.add(new User(3, "Atharva Mane", "Pune", "a@gmail.com", 7276624426l));
		userList.add(new User(4, "Chetan Sharma", "Mumbai", "c@gmail.com", 9307304361l));

		when(userService.getAllUser()).thenReturn(userList);

		ResponseEntity<List<User>> responseEntity = userController.getAllUsers();

		assertEquals(4, responseEntity.getBody().size());

		assertEquals(userList, responseEntity.getBody());

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	void testDeleteUser() {

		User user = new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l);

		userService.deleteUser(user.getUserId());

		ResponseEntity<ApiResponse> responseEntity = userController.deleteUser(user.getUserId());

		assertEquals("User deleted Successfully........", responseEntity.getBody().getMessage());

		assertEquals(true, responseEntity.getBody().getSuccess());

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	void testGetUserById() {
		
		Integer userId = 1;

		User user = new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l);

		when(userService.getUserById(userId)).thenReturn(user);
		
		ResponseEntity<User> responseEntity = userController.getUserById(userId);
		
		assertEquals(userId, responseEntity.getBody().getUserId());

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test 
	void testGetUserByName() {

		String name="Parshuram Bhosekar";
		
		User user = new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l);

		when(userService.getUserByName(name)).thenReturn(user);
		
		ResponseEntity<User> responseEntity = userController.getUserByName(name);
		
		assertEquals(name, responseEntity.getBody().getName());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertEquals(200, responseEntity.getStatusCodeValue());
	}
	
	@Test
	void testGetUserByCity() {
		
		List<User> userList = new ArrayList<>();
		userList.add(new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));
		userList.add(new User(2, "Sumit Pawar", "Sangli", "s@gmail.com", 9307304360l));
		userList.add(new User(3, "Atharva Mane", "Pune", "a@gmail.com", 7276624426l));
		userList.add(new User(4, "Chetan Sharma", "Sangli", "c@gmail.com", 9307304361l));
		
		String city="Sangli";
		
		when(userService.getUserByCity(city)).thenReturn(userList);
		
		ResponseEntity<List<User>> responseEntity = userController.getUserByCity(city);
		
		List<User> cityList = responseEntity.getBody().stream()
				.filter(user->user.getCity().equalsIgnoreCase("sangli"))
				.collect(Collectors.toList());
		
		assertEquals(3, cityList.size());
		
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		
		assertEquals(200, responseEntity.getStatusCodeValue());
	}
	

}
