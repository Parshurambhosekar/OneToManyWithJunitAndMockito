package com.parshuram.onetomanymapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.parshuram.onetomanymapping.entity.User;

public interface UserRepository extends JpaRepository<User, Integer>{

	List<User> findByCity(String city);
	
}
