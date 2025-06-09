package com.moview.UserRegistrationService.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.moview.UserRegistrationService.entity.Role;
import com.moview.UserRegistrationService.entity.User;
import com.moview.UserRegistrationService.exceptions.ResourceNotFoundException;
import com.moview.UserRegistrationService.repository.RoleRepository;
import com.moview.UserRegistrationService.repository.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	public ResponseEntity<?> getUserList() {
		try {
			List<User> users = userRepository.findAll().stream().filter(user->user.getRoles().isEmpty()==false).collect(Collectors.toList());
			if (!users.isEmpty())
				return new ResponseEntity<>(users, HttpStatus.OK);
			else if (users.isEmpty())
				return new ResponseEntity<>("No User record exist", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("No User record exist");

		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> getUserByUserId(long id) {
		try {
			Optional<User> users = userRepository.findById(id);
			if (!users.isEmpty())
				return new ResponseEntity<>(users, HttpStatus.OK);
			else if (users.isEmpty())
				return new ResponseEntity<>("No User record exist", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("No User record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> UpdateUser(User user, long id) {
		try {
			if (userRepository.findById(id).isPresent()) {
				User u = userRepository.findUserById(id);
				u.setId(user.getId());
				u.setName(user.getName());
				u.setUsername(user.getUsername());
				u.setEmail(user.getEmail());
				u.setPassword(user.getPassword());

				u.setRoles(user.getRoles());

				userRepository.save(user);
				return new ResponseEntity<>("User Details Updated", HttpStatus.OK);
			} else if (!userRepository.findById(id).isPresent())
				return new ResponseEntity<>("No User record exist", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("No User record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	public ResponseEntity<?> deleteUserById(long id) {
		try {
			if (userRepository.findById(id).isPresent()) {
				User u = userRepository.findUserById(id);
				u.setName(u.getName());
				u.setUsername(u.getUsername());
				u.setEmail(u.getEmail());
				u.setPassword(u.getPassword());
				u.setRoles(null);

				userRepository.save(u);

				return new ResponseEntity<>("User Deleted", HttpStatus.OK);
			} else if (!userRepository.findById(id).isPresent())
				return new ResponseEntity<>("No User record exist", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("No User record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	public ResponseEntity<?> makeAdmin(@PathVariable long id){
		try {
			if (userRepository.findById(id).isPresent()) {
				User user = userRepository.findUserById(id);
				User u = new User();
				u.setId(id);
				u.setName(user.getName());
				u.setUsername(user.getUsername());
				u.setEmail(user.getEmail());
				u.setPassword(user.getPassword());

				Role roles = roleRepository.findByName("ROLE_ADMIN").get();
				u.setRoles(Collections.singleton(roles));

				userRepository.save(u);
				return new ResponseEntity<>("User is ADMIN", HttpStatus.OK);
			} else if (!userRepository.findById(id).isPresent())
				return new ResponseEntity<>("No User record exist", HttpStatus.NOT_FOUND);
			else
				throw new ResourceNotFoundException("No User record exist");
		} catch (ResourceNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

}
