package com.cit.student_api_v2.enrollment.dto;

import java.time.LocalDateTime;

public record EnrollmentResponseWithStudent(
        Long enrollmentId,
        Long studentId,
        String studentRegistrationNumber,
        String studentName,
        LocalDateTime createdAt
) {
}
