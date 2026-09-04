package com.cit.student_api_v2.student.model;

import com.cit.student_api_v2.enrollment.model.Enrollment;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;


@Data
@Getter
@Setter
@NoArgsConstructor
@Entity
public class Student {
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

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.LAZY)
    private List<Enrollment> enrollments = new ArrayList<>();

    public Student(String name, String registrationNumber, Double cgpa,Status status) {
        this.name = name;
        this.registrationNumber = registrationNumber;
        this.cgpa = cgpa;
        this.status = status;
    }

}
