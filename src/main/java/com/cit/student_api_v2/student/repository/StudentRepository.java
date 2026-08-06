package com.cit.student_api_v2.student.repository;

import com.cit.student_api_v2.student.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Student findByRegistrationNumber(String registrationNumber);

    void deleteByRegistrationNumber(String registrationNumber);
}
