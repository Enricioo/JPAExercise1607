package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@CrossOrigin
@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping
	public List<User> getAllUtenti() {
		return userRepository.findAll();
	}

	@GetMapping("/users/minorenni")
	public List<User> getUserByEtaLessThan() {
		return userRepository.findByEtaLessThan(18);
	}

	@GetMapping("/users/user/{name}")
	public List<User> getUserByName(@PathVariable String name) {
		return userRepository.findByName(name);
	}

	@GetMapping("/users/{name}/{lastname}")
	public List<User> getUserByNameAndLastname(@PathVariable String name, @PathVariable String lastname) {
		return userRepository.findByNameAndLastname(name, lastname);
	}

	@GetMapping("/users/id/{id}")
	public User getUserById(@PathVariable Long id) {
		return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Id not found"));
	}

	@PostMapping
	public User newUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	@PutMapping("/users/{id}")
	public User updateUser(@PathVariable Long id, @RequestBody User userDetails) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		user.setName(userDetails.getName());
		user.setLastname(user.getLastname());
		user.setEta(user.getEta());
		return userRepository.save(user);
	}

	@DeleteMapping("/users/{id}")
	public void deleteCategory(@PathVariable Long id) {
		User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category not found"));
		userRepository.delete(user);
	}

}
