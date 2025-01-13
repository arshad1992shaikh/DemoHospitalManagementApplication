package com.hospital_management.demo_hospital_management.dtos;

import lombok.Data;

@Data
public class UserResponseDTO {
    private Long userId;
    private String username;
    private String roles;
    private HospitalResponseDTO hospital; // Add Hospital DTO to include hospital details
}
