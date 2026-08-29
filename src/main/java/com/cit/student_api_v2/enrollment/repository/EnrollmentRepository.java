package com.cit.student_api_v2.enrollment.repository;

import com.cit.student_api_v2.enrollment.model.Enrollment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {

    @Query("SELECT e FROM Enrollment e JOIN FETCH e.student WHERE e.id = :id")
    Optional<Enrollment> findByIdWithSummary(@Param("id") Long id);
}
