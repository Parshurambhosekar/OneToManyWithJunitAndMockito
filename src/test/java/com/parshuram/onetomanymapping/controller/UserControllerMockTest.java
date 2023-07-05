package com.parshuram.onetomanymapping.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parshuram.onetomanymapping.entity.User;
import com.parshuram.onetomanymapping.service.UserService;

@WebMvcTest(value = UserController.class)
class UserControllerMockTest {
	
	@MockBean
	private UserService userService;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	void testCreateUser() throws Exception {
		
		User user=new User(1, "Parshuram Bhosekar","Sangli", "p@gmail.com", 962377176l);
		
		when(userService.createUser(user)).thenReturn(user);
		
		ObjectMapper mapper=new ObjectMapper();
		String valueAsString = mapper.writeValueAsString(user);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users/")
															.contentType(MediaType.APPLICATION_JSON)
															.content(valueAsString);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(201, status);
		
		assertEquals(valueAsString, response.getContentAsString());
		
		assertEquals("application/json", response.getContentType());
	}
	
	@Test
	void testUpdateUser() throws Exception {
		
		User user=new User(1, "Parshuram Bhosekar","Sangli", "p@gmail.com", 962377176l);
		
		when(userService.updateUser(user, 1)).thenReturn(user);
		
		ObjectMapper mapper=new ObjectMapper();
		String valueAsString = mapper.writeValueAsString(user);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/users/{id}","1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(valueAsString);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(202, status);
		
		assertEquals(valueAsString, response.getContentAsString());
		
		assertEquals("application/json", response.getContentType());
	}
	
	@Test
	void testGetAllUserDetails() throws Exception {
		
		List<User> userList=new ArrayList<>();
		userList.add(new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));
		userList.add(new User(2, "Sumit Pawar", "Sangli", "s@gmail.com", 9307304360l));
		userList.add(new User(3, "Atharva Mane", "Pune", "a@gmail.com", 7276624426l));
		userList.add(new User(4, "Chetan Sharma", "Mumbai", "c@gmail.com", 9307304361l));
	
		when(userService.getAllUser()).thenReturn(userList);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
		
		assertEquals("application/json", response.getContentType());
	}
	
	@Test
	void testDeleteUser() throws Exception {
		
		User user=new User(1, "Parshuram Bhosekar","Sangli", "p@gmail.com", 962377176l);
		
		userService.deleteUser(user.getUserId());

		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/users/{id}","1");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
	}
	
	@Test
	void testGetUserById() throws Exception {
		
		User user=new User(1, "Parshuram Bhosekar","Sangli", "p@gmail.com", 962377176l);
		
		when(userService.getUserById(1)).thenReturn(user);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/{id}","1").contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
		
		assertEquals("application/json", response.getContentType());
	}
	
	@Test
	void testGetUserByName() throws Exception {
		
		User user=new User(1, "Parshuram Bhosekar","Sangli", "p@gmail.com", 962377176l);
		
		when(userService.getUserByName("Parshuram Bhosekar")).thenReturn(user);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/username/{name}","Parshuram Bhosekar");
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
		
		assertEquals("application/json", response.getContentType());
	}
	
	@Test
	void testGetUserByCity() throws Exception {
		
		List<User> userList=new ArrayList<>();
		userList.add(new User(1, "Parshuram Bhosekar", "Sangli", "p@gmail.com", 9623771726l));
		userList.add(new User(2, "Sumit Pawar", "Sangli", "s@gmail.com", 9307304360l));
		userList.add(new User(3, "Atharva Mane", "Pune", "a@gmail.com", 7276624426l));
		userList.add(new User(4, "Chetan Sharma", "Mumbai", "c@gmail.com", 9307304361l));
		
		when(userService.getUserByCity("Sangli")).thenReturn(userList);
		
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/users/usercity/{city}","Sangli").contentType(MediaType.APPLICATION_JSON);
		
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
		MockHttpServletResponse response = result.getResponse();
		
		int status = response.getStatus();
		
		assertEquals(200, status);
		
		assertEquals("application/json", response.getContentType());
	}

	
	
}
