package com.parshuram.onetomanymapping.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.parshuram.onetomanymapping.entity.ApiResponse;
import com.parshuram.onetomanymapping.entity.User;
import com.parshuram.onetomanymapping.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService=userService;
	}
	
	@PostMapping("/")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user){
		
		User createUser = userService.createUser(user);
		
		return new ResponseEntity<User>(createUser, HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@Valid @RequestBody User user,@PathVariable(name = "id") Integer userId){
		
		User updateUser = userService.updateUser(user, userId);
		
		return new ResponseEntity<User>(updateUser, HttpStatus.ACCEPTED);
		
	}
	
	@GetMapping("/")
	public ResponseEntity<List<User>> getAllUsers(){
		
		List<User> userList = userService.getAllUser();
		
		return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponse> deleteUser(@PathVariable(name = "id") Integer userId){
		
		userService.deleteUser(userId);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("User deleted Successfully........",true), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getUserById(@PathVariable(name = "id") Integer userId){
		
		User userById = userService.getUserById(userId);
		
		return new ResponseEntity<User>(userById, HttpStatus.OK);
	}
	
	@GetMapping("/username/{name}")
	public ResponseEntity<User> getUserByName(@PathVariable String name){
		
		User userByName = userService.getUserByName(name);
		
		return new ResponseEntity<User>(userByName, HttpStatus.OK);
	}
	
	@GetMapping("/usercity/{city}")
	public ResponseEntity<List<User>> getUserByCity(@PathVariable String city){
		
		List<User> userByCity = userService.getUserByCity(city);
		
		return new ResponseEntity<List<User>>(userByCity, HttpStatus.OK);
	}
	
}
