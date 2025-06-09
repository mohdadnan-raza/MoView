package com.moview.UserRegistrationService;

import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.moview.UserRegistrationService.entity.Role;
import com.moview.UserRegistrationService.entity.User;
import com.moview.UserRegistrationService.repository.RoleRepository;
import com.moview.UserRegistrationService.repository.UserRepository;
import com.moview.UserRegistrationService.services.UserServices;

@SpringBootTest(classes=com.moview.UserRegistrationService.UserRegistrationServiceApplication.class)
@ExtendWith(MockitoExtension.class)
class UserRegistrationServiceApplicationTests {
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;

	@InjectMocks
	private UserServices userServices;

	@Test
	void contextLoads() {
	}
  
	@Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {

		Role r1 = new Role();
		r1.setId(1);
		r1.setName("ROLE_ADMIN");
		
		Set<Role> r =Collections.singleton(r1);
        
        Optional<User> use = Optional.of(new User(1L, "test", "test", "test", "test", r));
        Mockito
        .when(userRepository.findById(1L))
        .thenReturn(use);
        
     ResponseEntity<?> use1 = userServices.getUserByUserId(1L);
        Assertions.assertEquals(use, use1.getBody());
    }
	
}


