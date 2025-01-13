package com.hospital_management.demo_hospital_management.services;

import com.hospital_management.demo_hospital_management.models.Hospital;
import com.hospital_management.demo_hospital_management.models.AuthRequest;
import com.hospital_management.demo_hospital_management.models.User;
import com.hospital_management.demo_hospital_management.repo.HospitalRepository;
import com.hospital_management.demo_hospital_management.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepository hospitalRepository;

    @Autowired
    private UserService userService;

    @Autowired
     private UserRepository userRepository;
    public List<Hospital> getAllHospitals() {
        return hospitalRepository.findAll();
    }

    public Hospital getHospitalById(Long id) {
        return hospitalRepository.findById(id).orElseThrow(() -> new RuntimeException("Hospital not found"));
    }

//    public Hospital saveHospital(Hospital hospital) {
//        // Temporarily detach the user to avoid saving it prematurely
//        User user = hospital.getUser();
//        hospital.setUser(null); // Avoid circular save during the first save of Hospital
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        User createdBy = userRepository.findByUsername(authentication.getName()).get();
//       hospital.setCreatedUser(createdBy);
//       hospital.setModifiedUser(createdBy);
//       hospital.setCreatedDate(new Date());
//       hospital.setModifiedDate(new Date());
//       hospital.setStatus(true);
//        // Save the hospital first to generate its ID
//        Hospital savedHospital = hospitalRepository.save(hospital);
//
//        // Now handle the user creation
//        if (user != null) {
//            // Set the current hospital ID to the user
//            user.setHospitalId(savedHospital);
//
//            // Save the user
//            User savedUser = userService.saveUser(user);
//            
//            // Link the user back to the hospital if needed
//            savedHospital.setUser(savedUser);
//            hospitalRepository.save(savedHospital);
//        }
//
//        return savedHospital;
//    }
    
    public Hospital saveHospital(Hospital hospital) {
        User user = hospital.getUser();  // Assuming you have a single user, not a list
        // Temporarily detach user from hospital to avoid premature save
        hospital.setUser(null);
        

        // Get the current authenticated user who will be the creator
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User createdBy = userRepository.findByUsername(authentication.getName()).get();

        // Set the audit fields
        hospital.setCreatedUser(createdBy);
        hospital.setModifiedUser(createdBy);
        hospital.setCreatedDate(new Date());
        hospital.setModifiedDate(new Date());
        hospital.setStatus(true);

        // Save the hospital first
        Hospital savedHospital = hospitalRepository.save(hospital);

        if (user != null) {
        	System.out.println("hello");
            // After saving the hospital, link the user to the saved hospital
            user.setHospitalId(savedHospital);

            // Set the current user to hospital's users list (if necessary)
            savedHospital.getUser(); // Assuming you want to add the user to the hospital's users list

            // Save the user
            User savedUser = userService.saveUser(user);

            // Link the user back to the hospital if necessary
            savedHospital.setUser(savedUser);  // Optional if you need to link back
            hospitalRepository.save(savedHospital);
        }
        
        return savedHospital;
    }


    public Hospital updateHospital(Long id, Hospital updatedHospital) {
        Hospital hospital = getHospitalById(id);
        // Update fields
        // ...
        return hospitalRepository.save(hospital);
    }

    public void deleteHospital(Long id) {
        hospitalRepository.deleteById(id);
    }
    
    public List<User> findUserByHospitalId(Long id) {
    	return this.hospitalRepository.findUsersByHospitalId(id);
    }
}
