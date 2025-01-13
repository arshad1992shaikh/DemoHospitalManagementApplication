package com.hospital_management.demo_hospital_management.services;

import com.hospital_management.demo_hospital_management.UserMapper;
import com.hospital_management.demo_hospital_management.dtos.UserResponseDTO;
import com.hospital_management.demo_hospital_management.exception.UserAlreadyExistsException;
import com.hospital_management.demo_hospital_management.exception.UserNotFoundException;
import com.hospital_management.demo_hospital_management.models.User;
import com.hospital_management.demo_hospital_management.repo.UserRepository;
import com.hospital_management.demo_hospital_management.templets.EmailTemplets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PasswordEncoder encoder;
    
    @Autowired
    private EmailService emailService;
    
    @Autowired
    private EmailTemplets emailTemplets;

    public List<User> getAllUsersByHospitalId(Long id) {
        return userRepository.findAllUsersByHospitalId(id);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
    }

    
    public User saveUser(User user) {
    	Optional<User> checkAlreadyExist = userRepository.findByUsername(user.getUsername());
    	
    	if (checkAlreadyExist.isPresent()) {
            throw new UserAlreadyExistsException("User with username " + user.getUsername() + " already exists.");
		}
    	String password = user.getPassword();
    	user.setPassword(encoder.encode(user.getPassword()));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User createdBy = userRepository.findByUsername(authentication.getName()).get();
        user.setCreatedUser(createdBy);
        user.setModifiedUser(createdBy);
        user.setCreatedDate(new Date());
        user.setModifiedDate(new Date());
        user.setStatus(true);
        if (user.getHospitalId()== null) {
          user.setHospitalId(createdBy.getHospitalId());
		}
        
        User savedUser = userRepository.save(user);
        emailService.sendEmail(user.getUsername(), "Welcome to "+createdBy.getHospitalId().getHospitalName()+" - Your Registration is Complete", emailTemplets.getRegistrationSuccessTemplate(createdBy.getHospitalId().getHospitalName(), savedUser.getUsername(), password, savedUser.getRoles(), savedUser.getUserId()));
       return savedUser;
    }

    public User updateUser(Long id, User updatedUser) {
        User user = getUserById(id);
        
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
//        User getModifyByUser =userRepository.findByUsername(authentication.getName()).orElseThrow(() -> new UserNotFoundException("User not found with this Username"));
                	
        user.setUsername(updatedUser.getUsername());
        user.setPassword(encoder.encode(updatedUser.getPassword()));
//        user.setModifiedUser(getModifyByUser);
        user.setModifiedDate(new Date());
        return userRepository.save(user);

    }
    
    
    public List<User> getAllActiveUsersByHospitalId(Long hospitalId) {
        return userRepository.findAllActiveUsersByHospitalId(hospitalId);
    }

    public void deleteUser(Long userId) {
        User user = getUserById(userId);

        user.setStatus(false); // Soft delete
        user.setModifiedDate(new Date());

        userRepository.save(user);
        
        emailService.sendEmail(user.getUsername(),"Your "+user.getHospitalId().getHospitalName()+" Account Has Been Deactivated", emailTemplets.getUserDeactivationTemplate(user.getUsername(), user.getHospitalId().getHospitalName()));
    }
    
    public User reactivateUser(Long userId) {
        // Fetch the user by ID
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Check if the user is already active
        if (Boolean.TRUE.equals(user.getStatus())) {
            throw new RuntimeException("User is already active.");
        }

        // Reactivate the user
        user.setStatus(true);
        user.setModifiedDate(new Date());
        emailService.sendEmail(user.getUsername(),"Your "+user.getHospitalId().getHospitalName()+" Account Has Been Reactivated", emailTemplets.getUserReactivationTemplate(user.getUsername(), user.getHospitalId().getHospitalName()));
        return userRepository.save(user);
    }

}

