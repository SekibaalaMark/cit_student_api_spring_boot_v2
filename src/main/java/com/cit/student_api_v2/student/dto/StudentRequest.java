package com.cit.student_api_v2.student.dto;

import jakarta.validation.constraints.*;

public class StudentRequest {
    @NotBlank(message="Name is required")
    private String name;

    @NotBlank(message = "Registration Number is Required")
    private String registrationNumber;

    @NotNull(message = "CGPA is required")
    @DecimalMin(value = "0.0", message = "GPA must be at least 0.0")
    @DecimalMax(value = "5.0", message = "GPA cannot exceed 5.0")
    private Double cgpa;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public Double getCgpa() {
        return cgpa;
    }

    public void setCgpa(Double cgpa) {
        this.cgpa = cgpa;
    }
}
