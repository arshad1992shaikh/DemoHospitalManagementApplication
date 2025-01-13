package com.hospital_management.demo_hospital_management.repo;

import com.hospital_management.demo_hospital_management.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String Username);

//	@Query("SELECT u FROM User u WHERE u.hospital.hospitalId = :hospitalId AND u.status = true")
//	List<User> findAllActiveUsersByHospitalId(@Param("hospitalId") Long hospitalId);
//
//	@Query("SELECT u FROM User u WHERE u.hospital.hospitalId = :hospitalId")
//	List<User> findAllUsersByHospitalId(@Param("hospitalId") Long hospitalId);
	
	@Query("SELECT u FROM User u WHERE u.hospitalId.hospitalId = :hospitalId AND u.status = true")
    List<User> findAllActiveUsersByHospitalId(@Param("hospitalId") Long hospitalId);

    @Query("SELECT u FROM User u WHERE u.hospitalId.hospitalId = :hospitalId")
    List<User> findAllUsersByHospitalId(@Param("hospitalId") Long hospitalId);

}
