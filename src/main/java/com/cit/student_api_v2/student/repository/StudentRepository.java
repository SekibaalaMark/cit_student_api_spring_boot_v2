package com.cit.student_api_v2.student.repository;

import com.cit.student_api_v2.student.model.Status;
import com.cit.student_api_v2.student.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findByRegistrationNumber(String registrationNumber);

    void deleteByRegistrationNumber(String registrationNumber);

    Page<Student> findByCgpaGreaterThanEqual(double cgpa, Pageable pageable);


    @Query("SELECT s from Student s JOIN FETCH s.enrollments WHERE s.status = :status")
    List<Student> findByStatusWithEnrollment(@Param("status") Status status);


}
