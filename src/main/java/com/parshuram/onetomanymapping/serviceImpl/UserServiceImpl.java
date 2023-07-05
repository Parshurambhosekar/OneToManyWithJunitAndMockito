package com.parshuram.onetomanymapping.serviceImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.parshuram.onetomanymapping.entity.User;
import com.parshuram.onetomanymapping.exception.ResourceNotFoundExcepton;
import com.parshuram.onetomanymapping.repository.UserRepository;
import com.parshuram.onetomanymapping.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository=userRepository;
	}
	
	
	@Override
	public User createUser(User user) {
	
		User createUser = userRepository.save(user);
		
		return createUser;
	}

	@Override
	public User updateUser(User user, Integer userId) {
		
		if(userRepository.existsById(userId)) {
			
			User updateUser = userRepository.save(user);
			
			return updateUser;
		}
		else {
			throw new ResourceNotFoundExcepton("User", "userId", userId);
		}	
		
	}

	@Override
	public List<User> getAllUser() {
		
		List<User> allUsers = userRepository.findAll();
		
		return allUsers;
	}

	@Override
	public void deleteUser(Integer userId) {
		
		if(userRepository.existsById(userId)) {
			
			userRepository.deleteById(userId);
		}
		else {
			throw new ResourceNotFoundExcepton("User", "userId", userId);
		}
	}

	@Override
	public User getUserById(Integer userId) {
		
		User getUser = userRepository.findById(userId)
			.orElseThrow(()->new ResourceNotFoundExcepton("User", "userId", userId));
		
		return getUser;
	}

	@Override
	public User getUserByName(String name) {
		
		List<User> lists=userRepository.findAll();
		
		User userByName = lists.stream()
			.filter(user->user.getName().equalsIgnoreCase(name))
			.findAny()
			.orElseThrow(()->new ResourceNotFoundExcepton("User","name", name));
			
		return userByName;
	}

	@Override
	public List<User> getUserByCity(String city) {

	 List<User> userByCity = userRepository.findByCity(city);
		
	 if(userByCity.isEmpty()) {
		 throw new ResourceNotFoundExcepton("User", "city", city);
	 }
	 
		return userByCity;
	}
	

}
