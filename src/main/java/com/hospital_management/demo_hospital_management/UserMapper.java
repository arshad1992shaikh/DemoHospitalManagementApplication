package com.hospital_management.demo_hospital_management;

import java.util.List;
import java.util.stream.Collectors;

import com.hospital_management.demo_hospital_management.dtos.HospitalResponseDTO;
import com.hospital_management.demo_hospital_management.dtos.UserResponseDTO;
import com.hospital_management.demo_hospital_management.models.Hospital;
import com.hospital_management.demo_hospital_management.models.User;

public class UserMapper {

    public static UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setUserId(user.getUserId());
        dto.setUsername(user.getUsername());
        dto.setRoles(user.getRoles());

        // Include Hospital details in the DTO response
        if (user.getHospitalId() != null) {
            dto.setHospital(toHospitalResponseDTO(user.getHospitalId()));
        }

        return dto;
    }
    
    public static List<UserResponseDTO> mapUsersToResponseDTOList(List<User> users) {
        return users.stream()
                    .map(user -> {
                        UserResponseDTO dto = new UserResponseDTO();
                        dto.setUserId(user.getUserId());
                        dto.setUsername(user.getUsername());
                        dto.setRoles(user.getRoles());

                        // Include Hospital details in the DTO response
                        if (user.getHospitalId() != null) {
                            dto.setHospital(toHospitalResponseDTO(user.getHospitalId()));
                        }

                        return dto;
                    })
                    .collect(Collectors.toList());
    }

    public static HospitalResponseDTO toHospitalResponseDTO(Hospital hospital) {
        HospitalResponseDTO hospitalDto = new HospitalResponseDTO();
        hospitalDto.setHospitalId(hospital.getHospitalId());
        hospitalDto.setHospitalName(hospital.getHospitalName());
        hospitalDto.setHospitalAddress(hospital.getHospitalAddress());
        hospitalDto.setHospitalContactNumber(hospital.getHospitalContactNumber());
        hospitalDto.setHospitalEmailId(hospital.getHospitalEmailId());
        return hospitalDto;
    }
}

