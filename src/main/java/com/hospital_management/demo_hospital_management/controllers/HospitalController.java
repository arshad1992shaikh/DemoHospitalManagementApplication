package com.hospital_management.demo_hospital_management.controllers;

import com.hospital_management.demo_hospital_management.UserMapper;
import com.hospital_management.demo_hospital_management.dtos.UserResponseDTO;
import com.hospital_management.demo_hospital_management.models.Hospital;
import com.hospital_management.demo_hospital_management.models.User;
import com.hospital_management.demo_hospital_management.services.HospitalService;
import com.hospital_management.demo_hospital_management.services.UserService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService hospitalService;

    @Autowired
    private UserService userService;
    
    @PostMapping("/addUser")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody User user) {
    	User user2 = userService.saveUser(user);
    	UserResponseDTO responseDTO = UserMapper.toUserResponseDTO(user2);
        return ResponseEntity.ok(responseDTO);
    }

    
    @GetMapping("/getUsers/{hospitalId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<List<UserResponseDTO>> getuserByHospitalId(@PathVariable Long hospitalId) {
        List<User> user = hospitalService.findUserByHospitalId(hospitalId);
        List<UserResponseDTO> response = UserMapper.mapUsersToResponseDTOList(user);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public Hospital updateHospital(@PathVariable Long id, @RequestBody Hospital hospital) {
        return hospitalService.updateHospital(id, hospital);
    }
    
    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<UserResponseDTO> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        UserResponseDTO response = UserMapper.toUserResponseDTO(user);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/all-users/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getAllUsers(@PathVariable Long id ) {
//        User user = userService.getUserById(id);
//        UserResponseDTO response = UserMapper.toUserResponseDTO(user);
        return this.userService.getAllUsersByHospitalId(id);
    }
    
    @GetMapping("/active-by-hospital/{hospitalId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public List<User> getAllActiveUsersByHospitalId(@PathVariable Long hospitalId) {
        return userService.getAllActiveUsersByHospitalId(hospitalId);
    }
    
    
    @DeleteMapping("/delete-user/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("User has been deactivated.");
    }
    
    @PutMapping("/reactivate/{userId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public User reactivateUser(@PathVariable Long userId) {
        return userService.reactivateUser(userId);
    }

   
}
