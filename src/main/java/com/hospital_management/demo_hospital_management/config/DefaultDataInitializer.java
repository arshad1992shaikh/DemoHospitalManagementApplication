package com.hospital_management.demo_hospital_management.config;

// Application Runner for Default Data
import com.hospital_management.demo_hospital_management.models.Hospital;
import com.hospital_management.demo_hospital_management.models.AuthRequest;
import com.hospital_management.demo_hospital_management.models.User;
import com.hospital_management.demo_hospital_management.repo.HospitalRepository;
import com.hospital_management.demo_hospital_management.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;
import java.util.Optional;

@org.springframework.context.annotation.Configuration
public class DefaultDataInitializer {

	   @Autowired
	    private PasswordEncoder encoder;

    @Bean
    public ApplicationRunner initializer(UserRepository userRepository, HospitalRepository hospitalRepository) {
        return args -> {
            String superUsername = "swapnilsudrik.s@gmail.com";
            String superPassword = "superadmin123";
            String superRole = "ROLE_SUPER_ADMIN";
            userRepository.findByUsername(superUsername).ifPresentOrElse(admin ->{
                System.out.println("SUPER ADMIN ALREADY EXIST.");
            }, ()->{
            	System.err.println("SUPER ADMIN NOT FOUND\n");
            	System.out.println("CREATING SUPER ADMIN FOR YOUR APPLICATION...");

                Hospital hospital = new Hospital();
                hospital.setHospitalName("Super Hospital");
                hospital.setHospitalAddress("pune");
                hospital.setHospitalContactNumber("7589874587");
                hospital.setHospitalEmailId("info@gmail.com");
                hospital.setHospitalTiming("10am to 10 pm");
                hospital.setHospitalLetterHead(null);
                hospital.setHospitalBillFormat("PDF");
                hospital.setHospitalWorkingDays("mon - sat");
                hospital.setHospitalOffDays("sun");
                hospital.setNumberOfUsers(1);
                hospital.setCreatedUser(null);
                hospital.setModifiedUser(null);
                hospital.setCreatedDate(new Date());
                hospital.setModifiedDate(new Date());
                hospital.setStatus(true);
                hospital.setUser(null);

                Hospital savedHospital = hospitalRepository.save(hospital);

                User user = new User();
                user.setUsername(superUsername);
                user.setPassword(encoder.encode(superPassword));
                user.setRoles(superRole);
                user.setHospitalId(savedHospital);
                user.setCreatedUser(null);
                user.setModifiedUser(null);
                user.setCreatedDate(new Date());
                user.setModifiedDate(new Date());
                user.setStatus(true);
                userRepository.save(user);


                System.out.println("SUPER ADMIN CREATED SUCCESS..");
                System.out.println("USERNAME: " +superUsername);
                System.out.println("PASSWORD: "+superPassword);
            });
        };
    }
}
