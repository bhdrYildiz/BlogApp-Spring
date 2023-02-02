package com.example.starbucks.controller;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.starbucks.entities.User;
import com.example.starbucks.repositories.UserRepository;
import com.example.starbucks.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@GetMapping
	public List<User> getallUsers(){
		return userService.getAllUsers();
	}
	
	@GetMapping("/{userId}")
	public User getOneUser(@PathVariable Long userId) {
		return userService.getOneUser(userId);
	}
	
	@PostMapping("new")
	public String createUser(@RequestBody User newUser) {
		userService.saveOneUser(newUser);
		return "new Student is saved";
	
	}
	@PutMapping("update/{userId}")
	public String updateOneUser(@PathVariable Long userId,@RequestBody User newUser) {
		userService.updateOneUser(userId,newUser);
		return "User updated!";
		}
	
	@PostMapping("delete/{userId}")
	public String deleteOneUser(@PathVariable Long userId) {
		
		userService.deleteOneUser(userId);
		return "user deleted";
	}
	
}
