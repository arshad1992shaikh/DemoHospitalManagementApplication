package com.hospital_management.demo_hospital_management.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Entity
@Data
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long hospitalId;

    @NotBlank(message = "Hospital name is mandatory")
    @Size(max = 100, message = "Hospital name cannot exceed 100 characters")
    private String hospitalName;
    
    @NotBlank(message = "Hospital address is mandatory")
    @Size(max = 255, message = "Hospital address cannot exceed 255 characters")
    private String hospitalAddress;
    
    @NotBlank(message = "Hospital contact number is mandatory")
    @Pattern(regexp = "\\d{10}", message = "Contact number must be 10 digits")
    private String hospitalContactNumber;
    
    @NotBlank(message = "Hospital email ID is mandatory")
    @Email(message = "Hospital email ID must be a valid email address")
    @Size(max = 100, message = "Email ID cannot exceed 100 characters")
    private String hospitalEmailId;
    
    @NotBlank(message = "Hospital timing is mandatory")
    @Size(max = 50, message = "Hospital timing cannot exceed 50 characters")
    private String hospitalTiming;

    @Lob
    private byte[] hospitalLetterHead;
    
    @Size(max = 100, message = "Bill format cannot exceed 100 characters")
    private String hospitalBillFormat;
    
    @Size(max = 100, message = "Working days cannot exceed 100 characters")
    private String hospitalWorkingDays;
    
    @Size(max = 100, message = "Off days cannot exceed 100 characters")
    private String hospitalOffDays;
    
    @NotNull(message = "Number of users is mandatory")
    @Min(value = 0, message = "Number of users cannot be negative")
    private Integer numberOfUsers;

    @ManyToOne
    private User createdUser;
    
    @ManyToOne
    private User modifiedUser;

    private Date createdDate;
    private Date modifiedDate;
    private Boolean status;

    @JsonManagedReference
    @ManyToOne
    @Valid
    private User user;
}
