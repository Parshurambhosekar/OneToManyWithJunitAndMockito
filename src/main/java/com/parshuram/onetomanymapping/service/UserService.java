package com.parshuram.onetomanymapping.service;

import java.util.List;

import com.parshuram.onetomanymapping.entity.User;

public interface UserService {
	
	public User createUser(User user);
	
	public User updateUser(User user,Integer userId);
	
	public List<User> getAllUser();
	
	public void deleteUser(Integer userId);
	
	public User getUserById(Integer userId);
	
	public User getUserByName(String name);
	
	public List<User> getUserByCity(String city);
	
	

}
