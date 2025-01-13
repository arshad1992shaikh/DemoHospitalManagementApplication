package com.hospital_management.demo_hospital_management.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;

@Entity
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotBlank(message = "Username is mandatory")
    @Email(message = "Username must be a valid email address")
    @Size(max = 100, message = "Username cannot exceed 100 characters")
    @Column(nullable = false, unique = true)
    private String username;
    
    @NotBlank(message = "Password is mandatory")
    private String password;
    private String roles;

    @ManyToOne
    @JsonBackReference
    private Hospital hospitalId;

    @ManyToOne
//    @JsonIgnore
    private User createdUser;

    @ManyToOne
//    @JsonIgnore
    private User modifiedUser;

    private Date createdDate;
    private Date modifiedDate;
    
    private Boolean status;

}

