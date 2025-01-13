package com.hospital_management.demo_hospital_management.controllers;

import com.hospital_management.demo_hospital_management.UserMapper;
import com.hospital_management.demo_hospital_management.dtos.UserResponseDTO;
import com.hospital_management.demo_hospital_management.exception.InvalidCredentialsException;
import com.hospital_management.demo_hospital_management.models.AuthRequest;
import com.hospital_management.demo_hospital_management.models.User;
import com.hospital_management.demo_hospital_management.services.JwtService;
import com.hospital_management.demo_hospital_management.services.UserService;

import org.hibernate.resource.transaction.internal.SynchronizationRegistryStandardImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;
    
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_DOCTOR','ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_NURSE')")
//    @PreAuthorize("hasAuthority('ROLE_DOCTOR')")
    public User updateUser(@PathVariable Long id, @RequestBody User user) {
    	System.out.println(user);
    	System.out.println("hello");
//    	User getUser = userService.updateUser(id, user);
//        UserResponseDTO response = UserMapper.toUserResponseDTO(getUser);
//        return ResponseEntity.ok(response);
    	return this.userService.updateUser(id, user);
    }

    @DeleteMapping("/delete/{userId}")
    @PreAuthorize("hasAnyAuthority('ROLE_DOCTOR','ROLE_SUPER_ADMIN', 'ROLE_ADMIN', 'ROLE_NURSE')")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User has been deactivated.");
    }

}

