package com.hospital_management.demo_hospital_management.repo;

import com.hospital_management.demo_hospital_management.models.Hospital;
import com.hospital_management.demo_hospital_management.models.User;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    @Query("SELECT u FROM User u WHERE u.hospitalId.hospitalId = :hospitalId")
    List<User> findUsersByHospitalId(@Param("hospitalId") Long hospitalId);
//	List<User> findByUserByHospitalId(Long id);
}

