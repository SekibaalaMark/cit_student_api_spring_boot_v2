package com.cit.student_api_v2.student.model;

import com.cit.student_api_v2.enrollment.model.Enrollment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Student {
    public Student() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 3 , max = 50, message = "name must be between 3 and 50 characters")
    @Column(nullable = false, length = 50)
    private String name;

    @Column(unique = true,nullable = false,length = 10)
    private String registrationNumber;

    @Min(value = 0, message = "CGPA cannot be below 0")
    @Max(value = 5, message = "CGPA cannot exceed 5")
    @Column(nullable = false)
    private Double cgpa;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Status status;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments = new ArrayList<>();


    public Student(String name, String registrationNumber, Double cgpa,Status status) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.cgpa = cgpa;
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

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

    public Long getId() {
        return id;
    }

    public List<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void setEnrollments(List<Enrollment> enrollments) {
        this.enrollments = enrollments;
    }
}
