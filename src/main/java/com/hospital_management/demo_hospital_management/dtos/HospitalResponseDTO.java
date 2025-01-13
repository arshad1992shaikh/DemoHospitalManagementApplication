package com.hospital_management.demo_hospital_management.dtos;

import lombok.Data;

@Data
public class HospitalResponseDTO {
    private Long hospitalId;
    private String hospitalName;
    private String hospitalAddress;
    private String hospitalContactNumber;
    private String hospitalEmailId;
}
