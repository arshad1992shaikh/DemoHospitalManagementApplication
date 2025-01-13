package com.hospital_management.demo_hospital_management.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_management.demo_hospital_management.dtos.LoginResponse;
import com.hospital_management.demo_hospital_management.exception.InvalidCredentialsException;
import com.hospital_management.demo_hospital_management.models.AuthRequest;
import com.hospital_management.demo_hospital_management.models.User;
import com.hospital_management.demo_hospital_management.repo.UserRepository;
import com.hospital_management.demo_hospital_management.services.JwtService;

@RestController
@RequestMapping("auth")
public class
AuthController {
	
	@Autowired
    private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserRepository userRepository;
    
    @Autowired
    private JwtService jwtService;
	
	@PostMapping("/login")
    public LoginResponse authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        try {
        	
        	 // Retrieve the user by username
            Optional<User> optionalUser = userRepository.findByUsername(authRequest.getUsername());
            
            if (optionalUser.isEmpty()) {
                throw new InvalidCredentialsException("Invalid username or password!");
            }

            User user = optionalUser.get();

            // Check if the user is active
            if (!Boolean.TRUE.equals(user.getStatus())) {
                throw new InvalidCredentialsException("User is not active. Please contact the administrator.");
            }

            
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
            if (authentication.isAuthenticated()) {
            	LoginResponse response = new LoginResponse();
            	User authenticatedUser = userRepository.findByUsername(authRequest.getUsername()).get();
            	response.setId(authenticatedUser.getUserId());
            	response.setRoles(authenticatedUser.getRoles());
            	response.setToken(jwtService.generateToken(authRequest.getUsername()));
       return response;
            }
//            return null;
            throw new InvalidCredentialsException("Authentication failed. Please check your credentials.");
        }catch (BadCredentialsException exception){
            throw new InvalidCredentialsException("Invalid username or password!");
        }
    }

}
