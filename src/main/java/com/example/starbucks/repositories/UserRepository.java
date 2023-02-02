package com.example.starbucks.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.starbucks.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
	
	
}
