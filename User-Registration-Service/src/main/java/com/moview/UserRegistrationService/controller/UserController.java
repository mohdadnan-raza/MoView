package com.moview.UserRegistrationService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moview.UserRegistrationService.entity.User;
import com.moview.UserRegistrationService.services.UserServices;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/user")
public class UserController {
	
	Logger LOGGER
    = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserServices service;

	// List of Users
	@GetMapping("/list")
	public ResponseEntity<?> viewUserList() {
		return service.getUserList();
	}

	// get user by user-id
	@GetMapping("/id/{id}")
	public ResponseEntity<?> getUserById(@PathVariable long id){
		return service.getUserByUserId(id);
	}

	// update user details by user-id
	@PutMapping("/update/id/{id}")
	public ResponseEntity<?> updateUser(@PathVariable long id, @RequestBody User user) {
		return service.UpdateUser(user, id);
	}

	// Delete user by user-id
	@DeleteMapping("/delete/id/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable long id){
		return service.deleteUserById(id);
	}
}
