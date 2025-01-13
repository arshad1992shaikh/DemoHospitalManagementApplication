package com.hospital_management.demo_hospital_management.dtos;

import lombok.Data;

@Data
public class LoginResponse {
	private Long id;
	private String roles;
	private String token;
	

}
