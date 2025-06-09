package com.moview.UserRegistrationService.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moview.UserRegistrationService.services.UserServices;

@RestController
public class AdminController {
	
	Logger LOGGER
    = LoggerFactory.getLogger(AdminController.class);

	@Autowired
	private UserServices userServices;
	
	@GetMapping("/")
	public String Home() {
		return "WELCOME TO MOVIEW";
	}
	
	//make admin2
	@PostMapping("/user/make/admin/id/{id}")
	public ResponseEntity<?> makeUserAdmin(@PathVariable long id){
		return userServices.makeAdmin(id);
	}

}
