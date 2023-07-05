package com.parshuram.onetomanymapping.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.parshuram.onetomanymapping.entity.User;
import com.parshuram.onetomanymapping.repository.UserRepository;
import com.parshuram.onetomanymapping.serviceImpl.UserServiceImpl;

@SpringBootTest
public class UserServiceMockTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserServiceImpl userService;

	@Test
	void testCreateUser() {

		User user = new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l);

		when(userRepository.save(user)).thenReturn(user);

		User createUser = userService.createUser(user);

		assertEquals(user, createUser);
	}

	@Test
	void testUpdateUser() {

		Integer userId = 1;

		User user = new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l);

		when(userRepository.existsById(userId)).thenReturn(true);

		when(userRepository.save(user)).thenReturn(user);

		User updateUser = userService.updateUser(user, user.getUserId());

		assertEquals(userId, updateUser.getUserId());
	}

	@Test
	void testGetAllUsers() {

		List<User> userList = new ArrayList<>();
		userList.add(new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));
		userList.add(new User(2, "Sumit Pawar", "Sangli", "s@gmail.com", 9307304360l));
		userList.add(new User(3, "Atharva Mane", "Pune", "a@gmail.com", 7276624426l));
		userList.add(new User(4, "Chetan Sharma", "Mumbai", "c@gmail.com", 9307304361l));

		when(userRepository.findAll()).thenReturn(userList);

		List<User> list = userService.getAllUser();

		assertEquals(4, list.size());

		assertEquals(userList, list);
	}

	@Test
	void testDeleteUser() {
		
		User user = new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l);

		when(userRepository.existsById(user.getUserId())).thenReturn(true);
		
		userService.deleteUser(user.getUserId());
		
		verify(userRepository,times(1)).deleteById(user.getUserId());	
	}
	
	@Test  
	void testGetUserById() {
		
		Integer userId=1;
		
		User user=new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l);
	
		when(userRepository.findById(user.getUserId())).thenReturn(Optional.ofNullable(user));
		
		User userById = userService.getUserById(userId);
		
		assertEquals(userId, userById.getUserId());
	}
	
	@Test
	void testGetUserByName() {
		
		String name="Parshuram Bhosekar";
		
		List<User> userList = new ArrayList<>();
		userList.add(new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));
		userList.add(new User(2, "Sumit Pawar", "Sangli", "s@gmail.com", 9307304360l));
		userList.add(new User(3, "Atharva Mane", "Pune", "a@gmail.com", 7276624426l));
		userList.add(new User(4, "Chetan Sharma", "Mumbai", "c@gmail.com", 9307304361l));
		
		when(userRepository.findAll()).thenReturn(userList);
		
		User userByName = userService.getUserByName(name);
		
		assertEquals(name, userByName.getName());
	}
	
	@Test
	void testGetUserByCity() {
		
		String city="Sangli";
		
		List<User> userList = new ArrayList<>();
		userList.add(new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));
		userList.add(new User(2, "Sumit Pawar", "Sangli", "s@gmail.com", 9307304360l));
		userList.add(new User(3, "Atharva Mane", "Pune", "a@gmail.com", 7276624426l));
		userList.add(new User(4, "Chetan Sharma", "Mumbai", "c@gmail.com", 9307304361l));
	
		when(userRepository.findByCity(city)).thenReturn(userList);
		
		List<User> userByCity = userService.getUserByCity(city);
		
		List<User> cityList = userByCity.stream()
			.filter(user->user.getCity().equalsIgnoreCase("sangli"))
			.collect(Collectors.toList());
		
		assertEquals(2, cityList.size());
	}
	
	
	

}
