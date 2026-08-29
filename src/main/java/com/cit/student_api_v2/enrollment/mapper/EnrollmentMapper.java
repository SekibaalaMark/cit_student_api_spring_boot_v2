package com.cit.student_api_v2.enrollment.mapper;



import com.cit.student_api_v2.enrollment.dto.EnrollmentResponse;
import com.cit.student_api_v2.enrollment.model.Enrollment;
import org.springframework.stereotype.Component;

@Component
public class EnrollmentMapper {

    public EnrollmentResponse toResponse(Enrollment enrollment) {
        return new EnrollmentResponse(
                enrollment.getId(),
                enrollment.getStudent().getId(),
                enrollment.getCreatedAt()
        );
    }
}