package com.hospital_management.demo_hospital_management.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hospital_management.demo_hospital_management.models.User;
import com.hospital_management.demo_hospital_management.repo.UserRepository;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserRepository repository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDetail = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));// Assuming 'email' is used as username

        // Converting UserInfo to UserDetails
        return new org.springframework.security.core.userdetails.User(userDetail.getUsername(), userDetail.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(userDetail.getRoles())));
//        		userDetail.map(UserInfoDetails::new)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + username));
    }
}

