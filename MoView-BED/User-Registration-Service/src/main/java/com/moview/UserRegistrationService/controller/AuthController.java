package com.moview.UserRegistrationService.controller;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.moview.UserRegistrationService.Deo.LoginDeo;
import com.moview.UserRegistrationService.Deo.SignUpDeo;
import com.moview.UserRegistrationService.Utility.JWTResponse;
import com.moview.UserRegistrationService.Utility.JWTUtility;
import com.moview.UserRegistrationService.entity.Role;
import com.moview.UserRegistrationService.entity.User;
import com.moview.UserRegistrationService.repository.RoleRepository;
import com.moview.UserRegistrationService.repository.UserRepository;
import com.moview.UserRegistrationService.security.CustomUserDetailsService;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

	Logger LOGGER
    = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private JWTUtility jwtUtility;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	
	
	@PostMapping("/login")
	public JWTResponse authenticateUser(@RequestBody LoginDeo loginDeo) throws Exception {
		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginDeo.getUsernameOrEmail(), loginDeo.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);

			UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginDeo.getUsernameOrEmail());
			String token = jwtUtility.generateToken(userDetails);
			return new JWTResponse(token);
		} catch (BadCredentialsException e) {
			throw new Exception("Invalid Credentials");
		}
	}

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@RequestBody SignUpDeo signUpDeo) {

		// check for username exists MoView db
		if (userRepository.existsByUsername(signUpDeo.getUsername())) {
			return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
		}

		// check for email exists in MoView db
		if (userRepository.existsByEmail(signUpDeo.getEmail())) {
			return new ResponseEntity<>("Email is already taken!", HttpStatus.BAD_REQUEST);
		}

		// create user object
		User user = new User();
		user.setName(signUpDeo.getName());
		user.setUsername(signUpDeo.getUsername());
		user.setEmail(signUpDeo.getEmail());
		user.setPassword(passwordEncoder.encode(signUpDeo.getPassword()));

		Role roles = roleRepository.findByName("ROLE_USER").get();
		user.setRoles(Collections.singleton(roles));

		userRepository.save(user);

		return new ResponseEntity<>("User registered successfully", HttpStatus.OK);

	}

}
