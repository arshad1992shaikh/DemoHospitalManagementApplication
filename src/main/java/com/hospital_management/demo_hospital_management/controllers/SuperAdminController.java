package com.hospital_management.demo_hospital_management.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hospital_management.demo_hospital_management.models.Hospital;
import com.hospital_management.demo_hospital_management.services.HospitalService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/super-admin")
public class SuperAdminController {

	@Autowired
	private HospitalService hospitalService;
	
	 @PostMapping("/add-hospital")
	    @PreAuthorize("hasAuthority('ROLE_SUPER_ADMIN')")
	    public Hospital createHospital(@Valid @RequestBody Hospital hospital) {
	        return hospitalService.saveHospital(hospital);
	    }
	 
	    @GetMapping
	    public List<Hospital> getAllHospitals() {
	        return hospitalService.getAllHospitals();
	    }

	    @GetMapping("/{id}")
	    public Hospital getHospitalById(@PathVariable Long id) {
	        return hospitalService.getHospitalById(id);
	    }
	    
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteHospital(@PathVariable Long id) {
	        hospitalService.deleteHospital(id);
	        return ResponseEntity.noContent().build();
	    }
}
